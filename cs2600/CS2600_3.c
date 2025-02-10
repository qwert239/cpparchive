#include <stdio.h>

int main(int argc, char *argv[]) {
    int n[3];

    printf("Input three different integers: ");
    scanf("%d %d %d", &n[0], &n[1], &n[2]);

    int sum=0,average,product=1,smallest=n[0],largest=n[0];
    for (int i=0;i<3;i++) {
        sum+=n[i];
        product*=n[i];
        if (n[i]<smallest) smallest=n[i];
        else if (n[i]>largest) largest=n[i];
    }
    average=sum/3;

    printf("Sum is %d\nAverage is %d\nProduct is %d\nSmallest is %d\nLargest is %d\n",sum,average,product,smallest,largest);
    

}