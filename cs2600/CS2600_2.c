#include <stdio.h>
#include <stdlib.h>

typedef struct {
    float **m;
    int rows, columns;
} matrix;

matrix multiply(matrix, matrix);
matrix make_matrix(int, int);
matrix fill_matrix(matrix);
void multiply_print();
void print_matrix(matrix);

int main(){
    multiply_print();
}

matrix multiply(matrix a, matrix b){
    if (a.columns!=b.rows) {
        printf("Invalid matrix size\n");
        exit(1);
    }
    matrix res=make_matrix(a.rows,b.columns); float value;

    for (int i=0;i<res.rows;i++) {
        for (int j=0;j<res.columns;j++) {
            value=0;
            for (int k=0; k<a.columns;k++) {
                value+=(a.m[i][k]*b.m[k][j]);
            }
            res.m[i][j]=value;
        }
    }
    return res;
}

matrix make_matrix(int r, int c) {
    matrix a; a.rows=r; a.columns=c;
    a.m=(float **)malloc(a.rows * sizeof(float *));
    for (int i=0; i<a.rows; i++) {
        a.m[i]=(float *)malloc(a.columns * sizeof(float));
    }
    return a;
}

matrix fill_matrix(matrix a) { // fill with user input
    float value;
    for (int i = 0; i < a.rows; i++) {
        for (int j = 0; j < a.columns; j++) {
            if (scanf("%f", &value) != 1) {
                printf("Invalid number of arguments\n");
                exit(1);
            }
            a.m[i][j] = value;
        }
    }
    
    return a;
}


void multiply_print() {
    int num1, num2;
    printf("Separate all elements by space.\n");

    printf("Size of matrix A: ");
    scanf("%d %d", &num1, &num2);
    matrix matrixA=make_matrix(num1,num2);
    printf("Elements of matrix A: ");
    fill_matrix(matrixA);
    print_matrix(matrixA);

    printf("Size of matrix B: ");
    scanf("%d %d", &num1, &num2);
    matrix matrixB=make_matrix(num1,num2);
    printf("Elements of matrix B: ");
    fill_matrix(matrixB);
    print_matrix(matrixB);

    matrix res=multiply(matrixA,matrixB);

    printf("Multiplication result:\n");
    print_matrix(res);
    
}

void print_matrix(matrix a){
    for (int i=0;i<a.rows;i++){
        for (int j=0;j<a.columns;j++){
            printf("%5.0f",a.m[i][j]);
        }
        putchar('\n');
    }
}