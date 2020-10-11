if [ -z $1 ]
    then
        PARSER="PCodeParser.jj"
    else
        PARSER=$1
fi

if [ -z $2 ]
    then
        PCODE="program.pcode"
    else
        PCODE=$2
fi

javacc $PARSER
javac $(find -name "*.java") -d bin
java -classpath bin Main $PCODE