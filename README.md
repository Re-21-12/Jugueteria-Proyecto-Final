# Jugueteria-Proyecto-Final
## Diagrama ER Base de datos
![Untitled](https://github.com/Re-21-12/Jugueteria-Proyecto-Final/assets/104967229/e98a18a5-dc35-4ce6-b66f-f7d39fe8e38d)

## Diagrama UML

## Documentacion Codigo

# Documentación del Código - ProcesosJugueteria

A continuación, se proporciona una documentación en formato Markdown para el código de la clase `ProcesosJugueteria`. Esta documentación describe la estructura del código y proporciona una visión general de su funcionalidad.

## Resumen

La clase `ProcesosJugueteria` es parte de un sistema de gestión de una juguetería y contiene métodos para realizar diversas operaciones, como revisar el inventario, actualizar juguetes, eliminar juguetes e insertar nuevos juguetes junto con la información relacionada, como distribuidores, clientes y facturas.

## Atributos

- `url`: Cadena de conexión a la base de datos MariaDB.
- `user`: Nombre de usuario para la conexión a la base de datos.
- `password`: Contraseña del usuario para la conexión a la base de datos.
- `conexion`: Objeto de conexión a la base de datos.
- `statement`: Objeto de declaración utilizado para ejecutar consultas SQL.
- `juguetes`: Lista de objetos `Juguete`.
- `distribuidores`: Lista de objetos `Distribuidor`.
- `clientes`: Lista de objetos `Cliente`.
- `inventario`: Objeto `Inventario` que almacena las listas de juguetes, distribuidores y clientes.

## Métodos

### `revisarInventario`

```java
public void revisarInventario(Inventario inventario) throws SQLException
