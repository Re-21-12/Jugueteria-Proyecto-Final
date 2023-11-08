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

- `revisarInventario`
    Este metodo revisa los juguetes que se encuentran en la tabla juguete de la base de datos generando una revision de los productos en el inventario.

- `actualizarJuguete`

    Este método recupera información sobre los juguetes desde la base de datos y actualiza la lista de juguetes en el objeto inventario.

    ```java
    public void actualizarJuguete(Juguete juguete, String nombreDistribuidor, String nombreCliente) throws SQLException
    ```

- `eliminarJuguete`

    Este método actualiza la información de un juguete en la base de datos y, si es necesario, actualiza también la información del distribuidor y del cliente relacionados con ese juguete.

    ```java
    public void eliminarJuguete(Integer id) throws SQLException
    ```

- `insertarJuguete`

    Este método elimina un juguete de la base de datos, primero eliminando los registros relacionados en la tabla distribuidor y luego el registro del juguete en la tabla juguete.

    ```java
    public Factura insertarJuguete(Juguete juguete, String nombreDistribuidor, String nombreCliente) throws SQLException
    ```

**Métodos Auxiliares**

Además de los métodos mencionados anteriormente, la clase contiene varios métodos auxiliares que se utilizan en las operaciones principales. Estos métodos auxiliares incluyen:

- `insertarDistribuidor`: Inserta un registro en la tabla distribuidor relacionado con un juguete.
- `insertarFactura`: Inserta un registro en la tabla factura relacionado con un juguete.
- `insertarCliente`: Inserta un registro en la tabla cliente relacionado con un juguete.
- `selectDistribuidor`: Recupera información sobre el distribuidor relacionado con un juguete.
- `selectCliente`: Recupera información sobre el cliente relacionado con un juguete.
- `generarId`: Genera un número de identificación aleatorio.

**Uso**

La clase `ProcesosJugueteria` se utiliza como parte de un sistema más amplio para gestionar la información de una juguetería. Proporciona funcionalidades para revisar, actualizar y eliminar juguetes, así como para insertar nuevos juguetes y gestionar la información relacionada con distribuidores, clientes y facturas.

La clase se comunica con una base de datos MariaDB a través de JDBC para realizar operaciones de lectura y escritura de datos.
