# TDD

## Concept 1

- Writing unit tests after the fact(implementation) is NOT Test-Driven-Development. It has nothing to do with TDD. It is a WASTE OF TIME.
- Why do you HAVE to write your tests first? Cause otherwise **you wouldn't have any code**.
- So, why must you write your tests first? "So **you know when you're done**".
- Why do we have a **suite of tests**? What is the **purpose of our suite of tests**?
  - So that we can **REFACTOR**!. It is not there to prove to other people that our code works. The suite of tests is there so that we can refactor!. So that **we're not afraid of our code**.
- How many of you are **afraid of your code**? Afraid of what will happen to you if you touch it? Code comes up on the screen, your first thought is "Someone should clean this!" Your next thought is: "I'm not touching it !"
  - Now **you are dominated by your own creation**, cause you're afraid to touch it! So it will ROT and will slow you down and the whole team down. Because you did not keep this beast under control.
- How do you keep the beast under control?
  - You **need a suite of tests** you trust with your life! You must never think "You know, I don't think I really tested everything..." Soon as you think that - you've lost it! Cause now you're afraid of your code.
- The reason we **write our tests first**, is so that we KNOW that every line of code we wrote, was **because of a failing test**. We know that **every single decision we made is tested**. So now we can pull up that code on our screen and **CLEAN IT Without any fear**.

https://www.youtube.com/watch?v=GvAzrC6-spQ

------

## Concept 2

TDD definition 

- You are not allowed to write any production code unless it is to make a fairly unit test pass.
- You are not allowed to write any more of a unit test than is sufficient to fail; and compilation failures are failures.
- You are not allowed to write any more production code than is sufficient to pass the one failing unit test.



rule number one says that if
you're writing production code it must
mean that you are actually trying to
make a failing unit test pass.
 so this is
basically saying that code is divided
into two categories production and unit
production code and you Nicole
and this rule dictates the order in
which you can write them number two
basically means that the amount of
unique test code you can write when you
are in that mode must be less than or
equal to the minimum that is needed for
the failing test to be death I'm afraid
so it basically means that the amount of
production code that you can write when
you are in that mode must be less than
or equal to the amount of code that is
needed to make the failing test pass so
to reiterate two modes of writing code
production and unit test number one is
about the order number two it's about
how much it can rise when you're in each
mode basically the bareness the bare
minimum so what are the implications it
means that we will always be compounding
and executing code so we should really
be looking at tests involve a Yi the gap
between tests the future should be less
than 10 minutes and the ideal situation
would be basically supposed to walk into
a roomful of developers and each one of
them will be able to say I'm gonna go or
code work
so the sky of methylamine that we coded
didn't color as expected you will be
just as matter of controls that I'm
doing rather than using the debugger and
we all know how horrendous it can be the
experience of debugging so we will be
constantly writing tests so by the day
it's like by the weeks by the mum's we
actually build update test Suites many
many tests hundreds even thousands of
tests so what are the benefits
will do basically we did extensive tests
which we have got something which known
as a living documentation
he's a documentation we can interact
with in some of the traditional style of
documentation which is in a written form
they are boring to read they get pretty
outraged quite soon and sometimes modern
immigrant you read them any we all know
the benefit of test and actually writing
test after code can be quite boring for
most will find a part boring and finding
out very boring process and also if you
write a test after you write a code you
may write code in such a way that it is
not so testable so I think we all know
the benefits of testing and in the
traditional way of writing the code
first before writing the test writing
the test is actually quite boring for
most people who most do developers and
if we write the test after we write the
code the comb might have been written in
a way that is not so testable so every
stuck actually so maybe you have to
reflect the code or we just don't test
certain code pops which is bad a lot of
people who have actually tried at EDD
planet a very fun process TDD is fun
writing test is fun making a test is fun
so you make the whole software
development rights income and much more
enjoyable experience so by definition
with TDD we have a hundred percent
testability
so we will have much better decoupling
and so our design will actually be much
better finally we are writing code for
the business to you finally were
actually writing after all we are
writing code for the business we use
this to use and with TDD all parties
understand it will be able to say the
program works and I'm sure that that
because the extensive test weight is
there and it's constantly running so he
never sales pitch for TDD I really
invites you to try out and he's the
length of the article half another day

https://www.youtube.com/watch?v=NML2VDrIHpc&feature=youtu.be