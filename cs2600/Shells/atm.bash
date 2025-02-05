#!/usr/bin/sh

savings="1000"
checking="1000"

get_pin() {
	attempts="0"
	while [ "$attempts" -lt "3" ]; do
		echo "*** Welcome to Cal Poly's ATM ***"
		echo "Please enter your PIN:"
		read pin
		if [ "$pin" -eq "111" ]; then
			return
		else
			attempts="$(($attempts + 1))"
			clear
		fi
	done

	echo "Too many illegal PINs. Try later again."
	exit
}

get_option() {
	clear
	echo "*** Welcome to Cal Poly's ATM ***"
	while [ "1" ]; do
	echo "(1) Transfer from checking account to savings account"
	echo "(2) Transfer from savings account to checking account"
	echo "(3) Savings account balance"
	echo "(4) Checking account balance"
	echo "(5) Withdraw cash from either account"
	echo "(6) Exit"
	echo "==> please select option (1-6):"
	read option
	if [ "$option" -ge "1" ] && [ "$option" -le "6" ]; then
		return
	else
		echo "Bad option."
		return_to_main
	fi
	done
}

option1() {
	clear
	echo "*** Welcome to Cal Poly's ATM System ***"
	echo "Enter amount to transfer"
	read a
	if [ "$a" -gt "$checking" ]; then
		echo "Transaction not completed: Current balance \$$checking"
		return_to_main
	else
		checking="$(($checking - $a))"
		savings="$(($savings + $a))"
		echo "Transaction complete"
		return_to_main
	fi
}

option2() {
	clear
        echo "*** Welcome to Cal Poly's ATM System ***"
        echo "Enter amount to transfer"
        read b
        if [ "$b" -gt "$savings" ]; then
                echo "Transaction not completed: Current balance \$$savings"
                return_to_main
        else
                checking="$(($checking + $b))"
                savings="$(($savings - $b))"
                echo "Transaction complete"
                return_to_main
        fi

}

option3() {
	clear
	echo "*** Welcome to Cal Poly's ATM System ***"
	echo "Savings account balance: \$$savings"
	return_to_main
}

option4() {
	clear
        echo "*** Welcome to Cal Poly's ATM System ***"
	echo "Checking account balance: \$$checking"
	return_to_main
}

option5() {
	clear
        echo "*** Welcome to Cal Poly's ATM System ***"
	echo "Enter 1 to withdraw from savings, 2 to withdraw from checking"
	read opt5
	case $opt5 in
		1)
			echo "Enter amount to withdraw:"
			read a
			if [ "$a" -gt "$savings" ]; then
				echo "Transaction not completed"
				return_to_main
			else
				savings="$(($savings - $a))"
				echo "Transaction complete"
				return_to_main
			fi
		;;
		2)
                        echo "Enter amount to withdraw:"
                        read a
                        if [ "$a" -gt "$checking" ]; then
                                echo "Transaction not completed"
                                return_to_main
                        else
                                checking="$(($checking - $a))"
                                echo "Transaction complete"
                                return_to_main
                        fi
                ;;
		*)
			echo "Bad option."
			return_to_main
		;;
	esac
}

return_to_main() {
	echo "Enter 1 to return to main menu, or anything else to quit"
	read a
	if [ "$a" -eq "1" ]; then
		main_menu
	else
		exit
	fi
}

main_menu() {
	clear
	get_pin
	get_option
	case $option in
		1)
			option1
		;;
		2)
			option2
		;;
		3)
			option3
		;;
		4)
			option4
		;;
		5)
			option5
		;;
		6)
			echo "Thank you for using the ATM system."
			exit
		;;
	esac
}

main_menu
