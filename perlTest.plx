$throw = length($ARGV[0]) % 6;
$guess = ($throw + 2) % 6;
print "$throw $guess \n";
