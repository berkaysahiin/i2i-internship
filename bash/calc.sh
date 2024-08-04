factorial() {
  if [ $1 -le 1 ]; then
    echo 1
  else
    echo $(( $1 * $(factorial $(( $1 - 1 ))) ))
  fi
}


echo "Choose an operand:"
echo "+ (addition)"
echo "- (subtraction)"
echo "* (multiplication)"
echo "/ (division)"
echo "% (modulo)"
echo "! (factorial)"

read -p "Enter operand: " operand

if [ "$operand" = "!" ]; then
	read -p "Enter number: " num1
else
	read -p "Enter first number: " num1
	read -p "Enter second number: " num2
fi

case $operand in 
	+)
		result=$((num1 + num2))
		echo "Result: $result"
		;;

	-)
		result=$((num1 - num2))
		echo "Result: $result"
		;;

	\*)
		result=$((num1 * num2))
		echo "Result: $result"
		;;

	/)
		result=$((num1 / num2))
		echo "Result: $result"
		;;
	%)
		result=$((num1 % num2))
		echo "Result: $result"
		;;

	!)
		result=$(factorial $num1)
		echo "Result: $result"
		;;
	*)
		echo "Invalid operations"
		;;
esac







