# Demos de Jobs en Talend DI
Ejemplos de Jobs creados en Talend Open Studio DI 7.3

## Requerimientos
+ Java 8 / OpenJdk 8
+ Talend Open Studio DI 7.3.1

## Ejemplos de Jobs

### 1. ListasControl
**a) ListaCia** descarga, a una carpeta local y comprime con zip, el listado 
de los [líderes mundiales publicados en la página web de la CIA][urlCIA].

El job guarda las urls y nombres de los países del menu de la [web][urlCIA]. Luego 
ingresa a cada url, obtiene el cargo y nombre de cada líder. Enseguida guarda en un archivo, 
por nombre de país, el cargo y líder. Al final, un script se encarga de comprimir la 
carpeta que tiene los archivos de países generados.

Los componentes utilizados son:
+ _tLibraryLoad_ para importar la librería _jsoup_ al job.
+ _tJava_ contiene el código que hace scrapping a la página web usando _jsoup_.
+ _tSystem_ ejecuta un script para comprimir los archivos descargados.

Las variables de contexto son:
+ _url_ que es la página <https://www.cia.gov/library/publications/world-leaders-1/>
+ _destino_ para indicar la ruta de la carpeta local. Ejemplo: C:/tmp
+ _extension_ para indicar la extension de los archivos a guardar. Predeterminado _.txt_
+ _comprime_ indica la ruta del script. Ejemplo C:/tmp/comprime.bat

El contenido del script *comprime.bat* es 
~~~
SET rutaCarpeta=%1
zip -r %rutaCarpeta%/listacia.zip %rutaCarpeta%
~~~

**b) ListaNegra** descarga la [lista de sanciones de la OFAC][urlOFAC] y 
la [lista del consejo de seguridad de la ONU][urlONU], y las envía un servidor remoto.

El job descarga la lista de la ONU y la guarda en disco. Luego crea una conexión 
con el servidor remoto, verifica que exista la lista y la envía a una carpeta remota por SFTP. 
Después descarga la lista de la OFAC y la guarda. Finalmente, verifica que exista esta última 
y la envía a la carpeta remota.

Los componentes utilizados son:
+ _tJava_ para obtener el usuario de *System.getProperty("user.name")* y la 
ip con *InetAddress.getLocalHost()*.
+ _tFileFetch_ descarga el archivo y guarda en una carpeta local
+ _tWarn_ para genear un mensaje personalizado cuando se ejecuta un componente. 
Tipo de prioridad: info, warn.
+ _tDie_ para generar un mensaje personalizado de error. Ejemplo mensaje + variable de componente. 
Tipo de prioridad: error.
+ _tFileExist_ para verificar que, el archivo descargado, se guardó correctamente.
+ _tFTPConnection_ conecta a un servidor remoto por el puerto 22 usando una llave privada. 
+ _tFTPPut_ envía los archivos al servidor remoto. Las lista descargadas en este caso.

Durante la ejecucción de los componentes, las acciones se van registrando en un log. 
Los componente adicionales para agregar el log, son:
+ _tLogCatcher_ escucha las excepciones de java, los errores y advertencias 
de los componentes al ejecutarse.
+ _tMap_ genera el escquema del log desde las variables y _tLogCatcher_. La estructura 
predeterminada es: fecha y hora, ip, usuario, proyecto, job, tipo de prioridad, componente de origen, mensaje.
+ _tReplicate_ separa el stream para el archivo log y para la consola.
+ _tFileOutputDelimited_ va agregando los registros al log y guarda en disco. 
Predeterminado *log.txt*
+ _tLogRow_ muestra el log en consola.

Las variables de contexto son:
+ _ruta_ es la ruta de la carpeta local.
+ _urlONU_ que es la url base <https://www.un.org/sc/suborg/sites/www.un.org.sc.suborg/files/>
+ _urlOFAC_ que es la url base <https://www.treasury.gov/ofac/downloads/>
+ _archivoONU_ es el archivo. Predeterminado _consolidated.xml_
+ _archivoOFAC_ es el archivo. Predeterminado _sdn.xml_
+ _ip_ contiene la ip del equipo local
+ _usuario_ contiene el usuario del equipo local
+ _rutaSftp_ indica la ruta de la carpeta remota. Predeterminado directorio raíz "/"
+ _servidorSftp_ es host o ip del servidor remoto
+ _usrSftp_ que es el usuario remoto
+ _puertoSftp_ el puerto remoto. Predeterminado 22
+ _pssSftp_ corresponde a la passphrase de la llave privada
+ _llavePrivadaSftp_ la ruta de la llave privada. Predeterminado */.ssh/id_rsa*

Ejemplo de log
~~~
2020-05-21 11:28:06;192.168.0.2;edwin;LISTASCONTROL;ListasNegras;tWarn;tWarn_1;Se conecta a la url de la ONU
2020-05-21 11:28:06;192.168.0.2;edwin;LISTASCONTROL;ListasNegras;tWarn;tWarn_3;Se descargó el archivo consolidated.xml
2020-05-21 11:28:28;192.168.0.2;edwin;LISTASCONTROL;ListasNegras;tWarn;tWarn_5;Se envío el archivo consolidated.xml y sdn.xml al servidor SFTP
2020-05-21 11:29:53;192.168.0.2;edwin;LISTASCONTROL;ListasNegras;tWarn;tWarn_2;Se conecta a la url de la OFAC
2020-05-21 11:29:53;192.168.0.2;edwin;LISTASCONTROL;ListasNegras;tWarn;tWarn_4;Se descargó el archivo sdn.xml
2020-05-21 11:31:52;192.168.0.2;edwin;LISTASCONTROL;ListasNegras;tWarn;tWarn_5;Se envío el archivo consolidated.xml y sdn.xml al servidor SFTP

~~~

[//]: # (referencias citadas)
[urlCIA]: https://www.cia.gov/library/publications/world-leaders-1/
[urlONU]: https://www.un.org/securitycouncil/content/un-sc-consolidated-list
[urlOFAC]: https://www.treasury.gov/resource-center/sanctions/SDN-List/Pages/default.aspx
