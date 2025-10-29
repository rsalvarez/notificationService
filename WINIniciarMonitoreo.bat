@echo off
REM ==============================
REM  Cargar variables de entorno
REM ==============================
for /f "usebackq tokens=1,* delims==" %%A in (`findstr /r "^[^#]" win_configmap.env`) do (
    set "%%A=%%B"
)

REM ==============================
REM  Ejecutar el servicio Java
REM ==============================
java -jar target\replicacion-service-1.0.0.jar

pause
