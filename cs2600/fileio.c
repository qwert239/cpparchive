#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    int i; float sum=0,average,product=1,smallest,largest;
    FILE *file=fopen(argv[2],"r");

    int arg_count=atoi(argv[1]);
    float *array=(float *)malloc(arg_count*sizeof(float));

    for (i=0;i<arg_count;i++){
        fscanf(file,"%f",&array[i]);
    }

    fclose(file);

    smallest=array[0]; largest=array[0];
    for (i=0;i<arg_count;i++){
        sum+=array[i];
        product*=array[i];
        if (array[i]<smallest) smallest=array[i];
        if (array[i]>largest) largest=array[i];
    }
    average=sum/arg_count;


    FILE *output_file=fopen(argv[3],"w");
    fprintf(output_file,"sum %.2f; average %.2f; product %.2f; smallest %.2f; largest %.2f",sum,average,product,smallest,largest);
    fclose(output_file);
    
}