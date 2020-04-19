### Classify programming languages

This is how you can classify programming languages:

- Compiled vs Interpreted programming languages 
- Statically typed vs Dynamically typed programming languages 

- Strongly typed vs Weakly typed programming languages.

### Compiled vs. Interpreted

> ```
> “When source code is translated”
> ```

- **Source Code**: Original code (usually typed by a human into a computer)
- **Translation**: Converting source code into something a computer can read (i.e. machine code)
- **Run-Time**: Period when program is executing commands (after compilation, if compiled)
- **Compiled**: Code translated before run-time
- **Interpreted**: Code **translated on the fly**, during execution

### Typing

> “When types are checked”
>
> **Type checking** - is just looking at variables and their types and then saying does this expression make sense.

#### Type Conversion

- In ***type conversion***, we want the output value to have a given type. If the input value already has that type, it is simply returned unchanged. Otherwise, it is converted to a value that has the desired type.
- *Explicit type conversion* (**Type casting**) means that the programmer uses an operation (a function, an operator, etc.) to trigger a type conversion. Explicit conversions can be:
  - *Checked*: If a value can’t be converted, an exception is thrown. *Type casting* is in Java is checked type conversion.
  - *Unchecked*: If a value can’t be converted, an error value is returned.
- ***Type coercion*** is *implicit type conversion*: An operation automatically converts its arguments to the types it needs. Can be <u>checked or unchecked</u> or something in-between. Type coercion is the ability for a value to <u>change type implicitly</u> in certain contexts (e.g. merging two types using `+`)

#### Strongly Typed Vs Weekly Typed languages

A **strongly-typed** language is one in which <u>variables are bound to specific data types</u>, and will result in type errors if types do not match up as expected in the expression,  **regardless of when type checking occurs**.  **Strongly typed** languages don't allow "**type coercion**" and will raise a type error. <u>Python, go and java</u> are strong-typed.

A **weakly-typed** language is a language in which variables are <u>not bound to a specific data type</u>; they still have a type, but **type safety constraints are lower** compared to strongly-typed languages. **Weakly typed** languages won't throw a type error for **type coercion**. <u>PHP, C and Javascript are weakly-typed</u>.

So `"3" + 5` will raise a type error in *strongly typed* languages and *weakly typed* languages result: `"35"`.

#### Statically typed vs dynamically typed languages

"Static" and "dynamic" are the two types of "typing". The "typing" word refers to "type" as in data type. 

##### Statically Typed

<u>In a statically typed language variables' **types are *static***</u>, meaning once you set a variable to a type, you cannot change it. That is because <u>typing is associated with the variable rather than the value it refers to</u>.

A language is statically typed **if the type of a variable is known at compile time**. For some languages this means that the **programmer must specify what type each variable** is (e.g.: Java, C, C++); other languages offer some form of ***type inference***, the <u>capability of the type system to deduce the type of a variable</u> (e.g.: OCaml, Haskell, Scala, Kotlin)

For example in Java:

```
String str = "Hello";  //variable str statically typed as string
str = 5;               //would throw an error since str is supposed to be a string only
```

Examples: C, C++, Java, Rust, Go, Scala

##### Dynamically Typed

<u>In a dynamically typed language variable types are ***dynamic***</u>, meaning **after you set a variable to a type, you CAN change it**. That is because <u>typing is associated with the value it assumes</u> rather than the variable itself. So a language is dynamically typed **if the type is associated with run-time values**. This means that the programmer do not have to specify types every time.

For example in Python:

```
str = "Hello" # variable str is linked to a string value
str = 5       # now it is linked to an integer value; perfectly OK
```

Examples: Perl, Ruby, Python, PHP, JavaScript

##### Compiled Vs interpreted

Statically typed languages are <u>generally compiled languages</u>, thus, the compilers check the types (as types are not allowed to be changed later on at run time), and therefore a lot of trivial bugs are caught at a very early stage. 

Dynamically typed languages are <u>generally interpreted</u>, thus <u>type checking (if any) happens at run time (on the fly, during execution) **when they are used**</u>. Most scripting languages have this feature as there is <u>no compiler to do static type-checking anyway</u>. This can cause errors due to the <u>interpreter misinterpreting the type of a variable</u>. This also brings some **performance cost**, and is one of the reasons dynamic languages <u>do not scale as good as the typed ones</u>. Means, statically typed languages have more of a start-up cost: makes you usually write more code, harder code. But that pays later off. Most dynamically typed languages <u>do allow you to provide type information, but do not require it</u>.

> **Note**: The definitions of “Static & Compiled” and “Dynamic & Interpreted” are quite similar. But remember it’s “**when types are checked**” vs. “**when source code is translated**”.
>
> ```
> Type-checking has nothing to do with the language being compiled or interpreted.
> ```

##### Example

Here is an example contrasting how Python (dynamically typed) and Go (statically typed) handle a type error:

> ```
> def silly(a):
>     if a > 0:
>         print 'Hi'
>     else:
>         print "3" + 5  // wrong usage
> ```

Because Python is <u>both interpreted and dynamically typed</u>, it only translates and type-checks code it’s executing on, therefore:  ` silly(2) ` Runs perfectly fine, and produces the output `Hi`. The `else` block never executes, so `"3" + 5` is never even looked at. Error is only raised if the problematic line is executed. So :  ` silly(-1) ` produces

> ```
> TypeError: unsupported operand type(s) for +: 'int' and 'str'
> ```

*<u>What if the language is statically typed</u>?*

A type error would be thrown **before the code is even run**. It <u>still performs type-checking before run-time</u> even though it is interpreted.

*What if the language is compiled?*

The `else` block would be translated/looked at before run-time, but because it's dynamically typed it wouldn't throw an error. Dynamically typed languages don't check types until execution, and that line never executes.

Go on the other hand is <u>static and compiled</u> so the above  code will not compile, with the error:

> ```
> invalid operation: "3" + 5 (mismatched types string and int)
> ```

#### Selection

When it comes to **technology selection**, neither side (statically / dynamically typed) has an intrinsic superiority over the other. It is just a matter of preference <u>whether you want more control to begin with or flexibility</u>. Flexibility lets you move faster, change things quickly. On the other side, control gives much more structure to a program and it’s a great aid <u>when working in teams</u>, where one single programmer can’t really have all the codebase in mind when working on it, and <u>helps keep the code more manageable</u>.

A <u>compiled language will have better performance at run-time</u> if it’s statically typed because the knowledge of types allows for **machine code optimization**. Statically typed languages have better performance at run-time intrinsically due to not needing to check types dynamically while executing (it checks before running). Similarly, compiled languages are faster at run time as the code has already been translated instead of needing to “interpret”/translate it on the fly.

![img](https://miro.medium.com/max/1648/1*BddwVWW6hFU0miT9DCbUWQ.png)



