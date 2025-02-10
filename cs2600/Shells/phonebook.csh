#!/bin/csh

while (1)
    echo "1. List records by First Name"
    echo "2. List records by Last Name"
    echo "3. List records by First Name (Reverse)"
    echo "4. List records by Last Name (Reverse)"
    echo "5. Search by Last Name"
    echo "6. Search by Birthday (Year or Month)"
    echo "7. Insert Record"
    echo "8. Delete Record"
    echo "9. Exit"
    echo -n "Select number: "
    set choice = $<

    switch ($choice)
        case 1:
            sort -k1 datebook.txt
            breaksw
        case 2:
            sort -k2 datebook.txt
            breaksw
        case 3:
            sort -k1 -r datebook.txt
            breaksw
        case 4:
            sort -k2 -r datebook.txt
            breaksw
        case 5:
            echo -n "Enter Last Name: "
            set last_name = $<
            grep -i "$last_name" datebook.txt
            breaksw
        case 6:
            echo -n "Enter Year or Month (YYYY or MM): "
            set date_search = $<
            if ( `echo $date_search | wc -c` == 5 ) then
                grep -i "${date_search}" datebook.txt
            else if ( `echo $date_search | wc -c` == 3 ) then
                grep -i ":$date_search/" datebook.txt
            else
                echo "Invalid format"
            endif
            breaksw
        case 7:
            echo -n "Enter Fullname: "
            set name = $<
            echo -n "Enter Phone Number: "
            set phone = $<
            echo -n "Enter Address: "
            set address = $<
            echo -n "Enter Birthday (MM/DD/YY): "
            set birthday = $<
            echo -n "Enter Salary: "
            set salary = $<
            echo "$name"':'"$phone"':'"$address"':'"$birthday"':'"$salary" >> datebook.txt
            sort -k1 datebook.txt > temp.txt && mv temp.txt datebook.txt
            echo "Record added"
            breaksw
        case 8:
            echo "Delete by:"
            echo "1. Phone Number"
            echo "2. Last Name"
            echo -n "Choose method: "
            set delete_method = $<
            if ($delete_method == 1) then
                echo -n "Enter Phone Number: "
                set phone = $<
                sed -i '/:'$phone':/d' datebook.txt
            else if ($delete_method == 2) then
                echo -n "Enter Last Name: "
                set last_name = $<
                sed -i '/ '$last_name':/d' datebook.txt
            else
                echo "Invalid option"
            endif
            breaksw
        case 9:
            sort -k1 datebook.txt > temp.txt && mv temp.txt datebook.txt
            echo "Exiting"
            exit
        default:
            echo "invalid option"
            breaksw
    endsw
end