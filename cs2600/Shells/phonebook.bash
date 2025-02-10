#!/bin/sh

datebook_file="datebook.txt"

sort_entries() {
    local sort_by=$1
    if [ "$sort_by" = "last" ]; then
        sort -k2,2 "$datebook_file"
    else
        sort -k1,1 "$datebook_file"
    fi
}

display_menu() {
    echo "Phonebook Menu:"
    echo "1. List by First Name (A-Z)"
    echo "2. List by Last Name (A-Z)"
    echo "3. List by First Name (Z-A)"
    echo "4. List by Last Name (Z-A)"
    echo "5. Search by Last Name"
    echo "6. Search by Birthday (Year or Month)"
    echo "7. Insert Record"
    echo "8. Delete Record"
    echo "9. Exit"
}

list_records() {
    local sort_by=$1
    local reverse=$2
    if [ "$reverse" = "true" ]; then
        sort_entries "$sort_by" | tac
    else
        sort_entries "$sort_by"
    fi
}

search_by_last_name() {
    local last_name=$1
    grep -i "^.* $last_name:" "$datebook_file"
}

search_by_birthday() {
    local criteria=$1
    grep -i ":$criteria$" "$datebook_file"
}

insert_record() {
    local record=$1
    echo "$record" >> "$datebook_file"
    sort -o "$datebook_file" "$datebook_file"
}

delete_record() {
    local identifier=$1
    if [[ $identifier =~ ^[0-9]{3}-[0-9]{3}-[0-9]{4}$ ]]; then
        sed -i "/:$identifier:/d" "$datebook_file"
    else
        sed -i "/ $identifier:/d" "$datebook_file"
    fi
}

while true; do
    clear
    display_menu
    read -p "Enter your choice: " choice

    case $choice in
        1) list_records "first" "false" | less ;;
        2) list_records "last" "false" | less ;;
        3) list_records "first" "true" | less ;;
        4) list_records "last" "true" | less ;;
        5) 
            read -p "Enter Last Name to Search: " last_name
            search_by_last_name "$last_name" | less
            ;;
        6) 
            read -p "Enter Birthday Year or Month (e.g., 92 or 1/15): " birthday
            search_by_birthday "$birthday" | less
            ;;
        7) 
            read -p "Enter Record (Format: First Last:XXX-XXX-XXXX:Address:DOB:Salary): " new_record
            insert_record "$new_record"
            echo "Record added and sorted."
            ;;
        8) 
            read -p "Enter Phone Number or Last Name to Delete: " identifier
            delete_record "$identifier"
            echo "Record deleted."
            ;;
        9) 
            sort -o "$datebook_file" "$datebook_file"
            echo "Records sorted and saved. Exiting"
            exit
            ;;
        *) echo "Bad option, Try again." ;;
    esac

    read -p "Press Enter to continue..."
done
