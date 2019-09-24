package src;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

class Interpolasi {

    Interpolasi () {}

    float XPangkat (float x, int n)
    {
       float hasil;
       int i;

       hasil = 1;
        for (i=1;i<=n;i++)
        {
            hasil = hasil * x; 
        }
        return hasil;
    }

    Matriks InputTitik ()
    {
        Matriks M = new Matriks ();

        int i,j,N;
        float x,y;
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan banyak titik: ");
        N = input.nextInt();

        M.NRowEff = N;
        M.NColEff = N+1;
    
        for (i=1;i<=N;i++)
        {   
            System.out.print("Masukkan titik ke-"+i+": ");
            M.Mat[i][1] = 1;
            x = input.nextFloat(); 
            y = input.nextFloat();
            M.Mat[i][N+1] =  y;
            for(j=2;j<=N;j++)
            {
                M.Mat[i][j] =  XPangkat(x,(j-1));
            }
        }

        return M;

    }

    float HasilInterpolasi (Matriks MI)
    {
        Matriks M = new Matriks();
        int i;
        float x,hasil;
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan x yang ingin dinterpolasi: ");
        x = input.nextFloat();
        hasil = 0;
        for (i = MI.RowMin; i <= MI.NRowEff; i++) {
          // System.out.println(MI.GetCoef().Crammer(MI.GetConstant(),i));
           hasil = hasil + (MI.GetCoef().Crammer(MI.GetConstant(),i) * XPangkat (x, i-1)) ; 
        }

        return hasil;

    }

    void TulisPersamaanPolinom (Matriks MI)
    {
        float a;
        int i;

        System.out.print("p");
        System.out.print(MI.NRowEff-1);
        System.out.print("(x) = ");

        for (i = MI.RowMin; i <= MI.NRowEff; i++) 
        {
            a = MI.GetCoef().Crammer(MI.GetConstant(),i);
            if ((i-1) == 0)
            {
                System.out.print(a);
              
            }
            else if ((i-1) == 1 && (a>0))
            {
                System.out.print(" + ");
                System.out.print(a);
                System.out.print("x");
            }
            else if ((i-1) == 1 && (a<0))
            {
                a = Math.abs(a);
                System.out.print(" - ");
                System.out.print(a);
                System.out.print("x");
            }
            else if ((i < MI.NRowEff) && ((i-1) > 1) && (a>0))
            {
                System.out.print(" + ");
                System.out.print(a);
                System.out.print("x^");
                System.out.print((i-1));
                
            }
            else if ((i < MI.NRowEff) && ((i-1) > 1) && (a<0))
            {
                a = Math.abs(a);
                System.out.print(" - ");
                System.out.print(a);
                System.out.print("x^");
                System.out.print((i-1));
                
            }
            else if ((i == MI.NRowEff) && ((i-1) > 1) && (a>0))
            {
                System.out.print(" + ");
                System.out.print(a);
                System.out.print("x^");
                System.out.print((i-1));
            }
            else 
            {
                a = Math.abs(a);
                System.out.print(" - ");
                System.out.print(a);
                System.out.print("x^");
                System.out.print((i-1));
                

            }
            
        }

    }

    
}