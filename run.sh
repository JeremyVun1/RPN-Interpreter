if [ -z $1 ]
    then
        echo "Specify parser file"
    else
        javacc $1
        javac $(find -name "*.java") -d bin
        java -classpath bin src/Main
fi