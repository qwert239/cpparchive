#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_TERMS 100

typedef struct {
    int *coefficients;
    int *exponents;
} polynomial;

polynomial get_polynomial();
void print_polynomial(polynomial);
polynomial add(polynomial a,polynomial b);
polynomial subtract(polynomial a,polynomial b);
polynomial multiply(polynomial a,polynomial b);

int main(int argc, char *argv[]) {
    int action;

    while (1) {
        printf("\nEnter polynomials in the following format: -4x^0 + x^1 + 4x^3 - 4x^5 - 3x^7\n");
        printf("Enter first polynomial: ");
        polynomial a=get_polynomial();
        printf("Enter second polynomial: ");
        polynomial b=get_polynomial();
        printf("1: addition\n2: subtraction\n3: multiplication\n4: enter two new polynomials\n5: exit\nEnter number of action to perform: ");
        
        scanf("%d",&action);
        switch (action) {
            case 1:
                print_polynomial(add(a,b));
                break;
            case 2:
                print_polynomial(subtract(a,b));
                break;
            case 3:
                print_polynomial(multiply(a,b));
            case 4:
                continue;
            case 5:
                exit(0);
        }
    }
}

polynomial get_polynomial() {
    polynomial a;

    a.coefficients=(int *)malloc(MAX_TERMS * sizeof(int));
    a.exponents=(int *)malloc(MAX_TERMS * sizeof(int));
    for (int i=0;i<MAX_TERMS;i++){
        a.coefficients[i]=0;
        a.exponents[i]=i;
    }

    int value1,value2; char operation;
    int x=1;
    while (1) {
        scanf("%dx^%d",&value1,&value2);
        

        if (x==1) a.coefficients[value2]+=value1;
        else if (x==-1) a.coefficients[value2]-=value1;
        
        scanf("%c",&operation); if (operation=='\n') break;
        if (scanf("%c ",&operation)!=1) break;
        //printf("%c",operation);
        
        else {
            if (operation=='+') x=1;
            else if (operation=='-') x=-1;
        }

    }
    
    return a;
    
}

void print_polynomial(polynomial a) {
    int last;
    for (int i=0;i<MAX_TERMS;i++){
        if (a.coefficients[i]!=0) last=i;

    }
    printf("Result: ");
    for (int i=0;i<MAX_TERMS;i++){
        if (a.coefficients[i]==0) continue;
        else {
            printf("%dx^%d",a.coefficients[i],i);
            if (i!=last) {
                printf(" + ");
            }
        }
    }
    putchar('\n');
}

polynomial add(polynomial a,polynomial b){
    polynomial res; 

    res.coefficients=(int *)malloc(MAX_TERMS * sizeof(int));
    res.exponents=(int *)malloc(MAX_TERMS * sizeof(int));

    for (int i=0;i<MAX_TERMS;i++){
        res.coefficients[i]=a.coefficients[i]+b.coefficients[i];
        res.exponents[i]=i;
    }
    return res;
}

polynomial subtract(polynomial a,polynomial b){
    polynomial res; 

    res.coefficients=(int *)malloc(MAX_TERMS * sizeof(int));
    res.exponents=(int *)malloc(MAX_TERMS * sizeof(int));

    for (int i=0;i<MAX_TERMS;i++){
        res.coefficients[i]=a.coefficients[i]-b.coefficients[i];
        res.exponents[i]=i;
    }
    return res;
}

polynomial multiply(polynomial a,polynomial b){
    polynomial res; 

    res.coefficients=(int *)malloc(MAX_TERMS * sizeof(int));
    res.exponents=(int *)malloc(MAX_TERMS * sizeof(int));

    for (int i=0;i<MAX_TERMS;i++) res.coefficients[i]=0;

    for (int i=0;i<MAX_TERMS;i++) {
        if (a.coefficients[i]!=0) {
            for (int j=0;j<MAX_TERMS;j++) 
                if (b.coefficients[j]!=0) 
                    res.coefficients[i+j]+=a.coefficients[i]*b.coefficients[j];
        }
    }

    return res;
}