# Jugueteria Proyecto Final Victor Macario 7690-22-5042

## Interfaz Grafica 
![image](https://github.com/Re-21-12/Jugueteria-Proyecto-Final/assets/104967229/f1aeca1a-b754-48f3-8b10-f214afb8d8d2)

## Diagrama ER Base de datos
![Untitled](https://github.com/Re-21-12/Jugueteria-Proyecto-Final/assets/104967229/e98a18a5-dc35-4ce6-b66f-f7d39fe8e38d)

## Diagrama UML

![jugueteria](https://github.com/Re-21-12/Jugueteria-Proyecto-Final/assets/104967229/dc33c7d5-d4cf-4d0a-b0d6-7c9d9f9b18b8)

## Documentacion Codigo
# Documentación del Código - ProcesosJugueteria

La clase `ProcesosJugueteria` es parte de un sistema de ventas de una juguetería, como:
1. revisar el inventario.
2. actualizar juguetes.
3. eliminar juguetes. 
4. insertar nuevos juguetes con base.

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

La clase `ProcesosJugueteria` se utiliza como clase para gestionar los procesos de una juguetería. Proporciona funciones para revisar, actualizar y eliminar juguetes, así como para insertar nuevos juguetes y gestionar la información relacionada con distribuidores, clientes y facturas.
La clase se comunica con una base de datos MariaDB a través de JDBC para realizar las distintas operaciones.

# Documentación de la Clase Ventana

La clase `Ventana` es una interfaz gráfica de usuario (GUI) utilizada para permitir a los usuarios realizar acciones como revisar el inventario, registrar una venta, actualizar información de juguetes y eliminar juguetes del inventario.

# Clase Ventana

La clase ventana en la funcion `createUI()` se encarga de generar la interfaz grafica junto con las disitintas funciones del _ABC_.
La funcion `actionPerformed()` se encarga de productir la logica en los campos y subirla haciendo uso de la clase `ProcesosJugueteria`.

Para generar los distintos campos usamos atributos tales como:
- Container panel;
- JTextField id;
- JTextField nombre;
- JTextField venta;
- JComboBox<String> estadoComboBox;
- JTextField nombreDistribuidor;
- JTextField cliente; 
