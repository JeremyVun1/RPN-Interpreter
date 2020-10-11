if [ -z $1 ]
    then
        PARSER="PCodeParser.jj"
    else
        PARSER=$1
fi

javacc $PARSER
javac $(find -name "*.java") -d bin
java -classpath bin Main