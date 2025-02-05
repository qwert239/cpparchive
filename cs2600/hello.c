#include <stdio.h>
#include <ctype.h>

void countCharacters(char*);

int main(int argc, char* argv[]) {
    countCharacters(argv[1]);
}

void countCharacters(char* filename) {
    FILE* file=fopen(filename,"r");
    char ch;
    int aCount=0, eCount=0, iCount=0, oCount=0, uCount=0, consonantCount=0, punctuationCount=0, charCount=0;
    while ((ch=fgetc(file)) != EOF) {
        if (ch=='a' || ch=='A') aCount++;
        else if (ch=='e' || ch=='E') eCount++;
        else if (ch=='i' || ch=='I') iCount++;
        else if (ch=='o' || ch=='O') oCount++;
        else if (ch=='u' || ch=='U') uCount++;
        else if (isalpha(ch)) consonantCount++;
        else if (ch!=' ' && ch!='\n') punctuationCount++;

        if (isalpha(ch) || (ch!=' ' && ch!='\n')) charCount++;
    }

    printf("Percentages of characters:\n");
    printf("a %d%%; e %d%%; i %d%%; o %d%%; u %d%%; consonants %d%%; punctuations and other characters %d%%\n",
        aCount*100/charCount,eCount*100/charCount,iCount*100/charCount,oCount*100/charCount,uCount*100/charCount,
        consonantCount*100/charCount,punctuationCount*100/charCount);

    fclose(file);

    FILE* outputFile=fopen("output.txt","w");
    fprintf(outputFile,"Percentages of characters:\n");
    fprintf(outputFile,"a %d%%; e %d%%; i %d%%; o %d%%; u %d%%; consonants %d%%; punctuations and other characters %d%%\n",
        aCount*100/charCount,eCount*100/charCount,iCount*100/charCount,oCount*100/charCount,uCount*100/charCount,
        consonantCount*100/charCount,punctuationCount*100/charCount);
    fclose(outputFile);
}