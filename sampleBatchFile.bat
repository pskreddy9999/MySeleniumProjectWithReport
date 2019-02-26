set projectPath=E:\workSpace\demo
cd %projectPath%

set classPath=%projectPath%\bin;%projectPath%\lib\*

java org.testng.TestNG %projectPath%\testng.xml

pause