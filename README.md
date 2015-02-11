# Morra
The Morra KOTH Tournament

The "Tournament" class is where execution starts.  The list of players is contained inside of it.

"Game" runs a single game between two players.

"Player" is used to create players in two ways:  A Java bot extends the Player class (so that they are categorized as "Player"), while a non-Java bot has a command used to run it.

"ExampleBot" is a simple Java submission.  The move() method is static and is where custom code goes.  The dynamic getMove() allows Game to access a static method using an object reference.

"perlTest.plx" is an example Perl competitor.
