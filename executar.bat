@echo off
REM Compilar todos os arquivos .java
javac *.java
if errorlevel 1 (
    echo Erro na compilacao. Verifique seu codigo.
    pause
    exit /b 1
)
REM Executar a classe principal
java MainApp
pause
