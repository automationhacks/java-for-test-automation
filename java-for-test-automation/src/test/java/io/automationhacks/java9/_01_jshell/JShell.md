# Jshell

Introduced in Java 9, JShell is an interactive REPL (Read-Eval-Print Loop) tool that allows us to execute Java code
snippets and get immediate results. It is a great tool for learning Java, testing small code snippets, and prototyping.

```shell
➜  java-for-test-automation git:(main) jshell
|  Welcome to JShell -- Version 21.0.2
|  For an introduction type: /help intro

jshell> "hey this is something.new".substring(5, 10)
$1 ==> "his i"

jshell>
```

We can save or open files using JShell.

```shell
/save /Users/gauravsingh/self/java-for-test-automation/java-for-test-automation/src/test/resources/script_output.txt
/open /Users/gauravsingh/self/java-for-test-automation/java-for-test-automation/src/test/resources/script_output.txt
```

To exit JShell, type `/exit` or press `Cmd + D`.