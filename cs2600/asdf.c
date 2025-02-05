#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool find_float(char *,int,float);

int main(int argc,char *argv[]) {
    if (argc!=1+3) {
        printf("Enter valid number of arguments\n");
        exit(1);
    }
    if (find_float(argv[1],atoi(argv[2]),atof(argv[3]))) {
        printf("Number found\n");
    }
    else printf("Number not found\n");
}

bool find_float(char *filename,int num_count,float num){
    FILE *file=fopen(filename,"r");
    float *array=(float *)malloc(num_count*sizeof(float));
    bool found=false;
    for (int i=0;i<num_count;i++){
        fscanf(file,"%f",&array[i]);
        if (num==array[i]) found=true;
    }
    fclose(file);
    for (int i=num_count-1;i>=0;i--){
        printf("%.2f ",array[i]);
    }
    putchar('\n');
    return found;
}