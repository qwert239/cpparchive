#!/usr/bin/csh

set wrongcount = 0
while (1)
	clear
	echo "*** Welcome to Cal Poly's ATM ***"
	echo -n "Please enter your PIN: "
	set pin = $<
	if ($pin == 111) then
		break
	else
		@ wrongcount++
	endif

	if ($wrongcount == 3) then
		echo "Too many illegal PINs. Try again later."
		exit
	endif
end

set checking = 1000
set savings = 1000

while (1)
	clear
	echo "*** Welcome to Cal Poly's ATM ***"
	echo "(1) Transfer from checking account to savings account"
	echo "(2) Transfer from savings account to checking account"
	echo "(3) Savings account balance"
	echo "(4) Checking account balance"
	echo "(5) Withdraw cash from either account"
	echo "(6) Exit"
	echo -n "==> Please select option (1-6): "
	set option = $<
	switch ($option)
		case 1:
			echo -n "Enter amount to transfer: "
			set amount = $<
			if ($amount <= $checking) then
				@ checking -= $amount
				@ savings += $amount
				echo "Transaction complete"
				echo "Returning to main menu..."
				sleep 3
			else
				echo "Transaction not complete, current balance: "'$'"$checking"
				echo "Returning to main menu..."
				sleep 3
			endif
		breaksw
		case 2:
			echo -n "Enter amount to transfer: "
			set amount = $<
			if ($amount <= $saving) then
				@ savings -= $amount
				@ checking += $amount
				echo "Transaction complete"
				echo "Returning to main menu..."
				sleep 3
			else
				echo "Transaction not complete, current balance: "'$'"$savings"
				echo "Returning to main menu..."
				sleep 3
			endif
		breaksw
		case 3:
			echo "Savings account balance: "'$'"$savings"
			echo "Returning to main menu..."
			sleep 3
		breaksw
		case 4:
			echo "Checking account balance: "'$'"$checking"
			echo "Returning to main menu..."
			sleep 3
		breaksw
		case 5:
			echo "(1) Withdraw from savings account"
			echo "(2) Withdraw from checking account"
			echo -n "==> Choose option (1-2): "
			set case5option = $<
			switch ($case5option)
				case 1:
					echo -n "Enter amount to withdraw: "
					set case5amount = $<
					if ($case5amount <= $savings) then
						@ savings -= $case5amount
						echo "Transaction complete"
						echo "Returning to main menu..."
						sleep 3
					else
						echo "Transaction not complete, current balance: "'$'"$savings"
						echo "Returning to main menu..."
						sleep 3
					endif
				breaksw
				case 2:
					echo -n "Enter amount to withdraw: "
					set case5amount = $<
					if ($case5amount <= $checking) then
						@ checking -= $case5amount
						echo "Transaction complete"
						echo "Returning to main menu..."
						sleep 3
					else
						echo "Transaction not complete, current balance: "'$'"$checking"
						echo "Returning to main menu..."
						sleep 3
					endif
				breaksw
				default:
					echo "Incorrect option"
					echo "Returning to main menu..."
					sleep 3
			endsw
		breaksw
		case 6:
			echo "Thank you for using the ATM system."
			exit
		breaksw
		default:
			echo "Invalid option"
			echo "Returning to main menu..."
			sleep 3
	endsw
end	
