const express = require('express');
const mysql = require('mysql2');

const app = express();
app.use(express.json());

// Conexión
const connection = mysql.createConnection(
    {
        host: 'localhost',
        user: 'root',
        password: '',
        database: 'estudiantes_project' //nombre bd
    });

connection.connect((err) =>
{
    if (err) throw err;
    console.log('Conectado a la base de datos MySQL');
});

// Crear estudiante/direcciones
app.post('/estudiantes', (req, res) =>
{
    const { estudiante, residencia, trabajo } = req.body;
    const { cedula, nombres, apellidos } = estudiante;

    connection.beginTransaction((err) =>
    {
        if (err) return res.status(500).send(err);

        const estudianteQuery = 'INSERT INTO estudiantes (cedula, nombres, apellidos) VALUES (?, ?, ?)';
        connection.query(estudianteQuery, [cedula, nombres, apellidos], (err, results) =>
        {
            if (err) {
                return connection.rollback(() =>
                {
                    res.status(400).send(err);
                });
            }

            const estudianteId = results.insertId;

            const ubicacionQuery = 'INSERT INTO ubicaciones (coordenadas) VALUES (POINT(?, ?)), (POINT(?, ?))';
            connection.query(ubicacionQuery, [residencia.longitud, residencia.latitud, trabajo.longitud, trabajo.latitud], (err, results) =>
            {
                if (err) {
                    return connection.rollback(() =>
                    {
                        res.status(400).send(err);
                    });
                }

                const residenciaId = results.insertId;
                const trabajoId = results.insertId + 1;

                const direccionQuery = 'INSERT INTO direcciones (estudiante_id, ubicacion_id, categoria, principal) VALUES (?, ?, ?, ?), (?, ?, ?, ?)';
                connection.query(direccionQuery, [estudianteId, residenciaId, 'residencia', true, estudianteId, trabajoId, 'trabajo', false], (err, results) =>
                {
                    if (err)
                    {
                        return connection.rollback(() =>
                        {
                            res.status(400).send(err);
                        });
                    }

                    connection.commit((err) =>
                    {
                        if (err)
                        {
                            return connection.rollback(() =>
                            {
                                res.status(500).send(err);
                            });
                        }

                        res.status(201).send({ id: estudianteId, cedula, nombres, apellidos, residencia, trabajo });
                    });
                });
            });
        });
    });
});

// Listar todos los estudiantes con sus direcciones
app.get('/estudiantes', (req, res) =>
{
    const query = `
        SELECT e.id, e.cedula, e.nombres, e.apellidos, 
               d.categoria, ST_X(u.coordenadas) AS longitud, ST_Y(u.coordenadas) AS latitud
        FROM estudiantes e
        LEFT JOIN direcciones d ON e.id = d.estudiante_id
        LEFT JOIN ubicaciones u ON d.ubicacion_id = u.id
    `;

    connection.query(query, (err, results) =>
    {
        if (err) return res.status(500).send(err);

        res.status(200).send(results);
    });
});

// Actualizar un estudiante/direcciones
app.put('/estudiantes', (req, res) =>
{
    const { estudiante, residencia, trabajo } = req.body;
    const { cedula, nombres, apellidos } = estudiante;

    connection.beginTransaction((err) =>
    {
        if (err) return res.status(500).send(err);

        const estudianteQuery = 'UPDATE estudiantes SET nombres = ?, apellidos = ? WHERE cedula = ?';
        connection.query(estudianteQuery, [nombres, apellidos, cedula], (err, results) =>
        {
            if (err) {
                return connection.rollback(() =>
                {
                    res.status(400).send(err);
                });
            }

            const ubicacionQuery = `
                UPDATE ubicaciones u
                JOIN direcciones d ON u.id = d.ubicacion_id
                JOIN estudiantes e ON d.estudiante_id = e.id
                SET u.coordenadas = POINT(?, ?)
                WHERE e.cedula = ? AND d.categoria = ?
            `;

            connection.query(ubicacionQuery, [residencia.longitud, residencia.latitud, cedula, 'residencia'], (err, results) =>
            {
                if (err) {
                    return connection.rollback(() =>
                    {
                        res.status(400).send(err);
                    });
                }

                connection.query(ubicacionQuery, [trabajo.longitud, trabajo.latitud, cedula, 'trabajo'], (err, results) =>
                {
                    if (err) {
                        return connection.rollback(() =>
                        {
                            res.status(400).send(err);
                        });
                    }

                    connection.commit((err) =>
                    {
                        if (err) {
                            return connection.rollback(() =>
                            {
                                res.status(500).send(err);
                            });
                        }

                        res.status(200).send({ cedula, nombres, apellidos, residencia, trabajo });
                    });
                });
            });
        });
    });
});

// Listar un estudiante por cédula/direcciones
app.get('/estudiantes/:cedula', (req, res) =>
{
    const { cedula } = req.params;

    const query = `
        SELECT e.id, e.cedula, e.nombres, e.apellidos, 
               d.categoria, ST_X(u.coordenadas) AS longitud, ST_Y(u.coordenadas) AS latitud
        FROM estudiantes e
        LEFT JOIN direcciones d ON e.id = d.estudiante_id
        LEFT JOIN ubicaciones u ON d.ubicacion_id = u.id
        WHERE e.cedula = ?
    `;

    connection.query(query, [cedula], (err, results) =>
    {
        if (err) return res.status(500).send(err);

        if (results.length === 0) return res.status(404).send();

        res.status(200).send(results);
    });
});

// Borrar un estudiante por cédula
app.delete('/estudiantes/:cedula', (req, res) =>
{
    const { cedula } = req.params;

    connection.beginTransaction((err) =>
    {
        if (err) return res.status(500).send(err);

        const estudianteQuery = 'DELETE FROM estudiantes WHERE cedula = ?';
        connection.query(estudianteQuery, [cedula], (err, results) =>
        {
            if (err) {
                return connection.rollback(() =>
                {
                    res.status(500).send(err);
                });
            }

            if (results.affectedRows === 0) {
                return connection.rollback(() =>
                {
                    res.status(404).send();
                });
            }

            const direccionQuery = `
                DELETE d FROM direcciones d
                JOIN estudiantes e ON d.estudiante_id = e.id
                WHERE e.cedula = ?
            `;

            connection.query(direccionQuery, [cedula], (err, results) =>
            {
                if (err) {
                    return connection.rollback(() =>
                    {
                        res.status(500).send(err);
                    });
                }

                connection.commit((err) =>
                {
                    if (err) {
                        return connection.rollback(() =>
                        {
                            res.status(500).send(err);
                        });
                    }

                    res.status(200).send({ message: 'Estudiante borrado exitosamente' });
                });
            });
        });
    });
});

// Iniciar el servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () =>
{
    console.log(`Servidor corriendo en el puerto ${PORT}`);
});



