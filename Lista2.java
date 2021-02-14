/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista2;

import java.util.Random;

public class Lista2 {

  
    public static void main(String[] args) 
    {
        //ArvoreSBB ordenada
        ArvoreSBB []arvoreSBB_ordenada=new ArvoreSBB[10];
        for(int i=0;i<10;i++)
        {
            arvoreSBB_ordenada[i]=new ArvoreSBB();
        }
        
        for(int i=0;i<10;i++)
        {
            long elementos = 10000*(i+1);
            for(int j=1;j<=elementos;j++)
            {
                Item itens=new Item(j);
                arvoreSBB_ordenada[i].insere(itens);
            }
        }
        System.out.println("");
        System.out.println("ARVORE ORDENADA:");
        //pesquisando itens nao contido
        Item aux=new Item(1000000);
        for(int i=0;i<10;i++)
        {
             arvoreSBB_ordenada[i].pesquisa(aux);
             //pesquisando itens nao contido
             System.out.println("Arvore "+(i+1)+"  nó visitados = "+arvoreSBB_ordenada[i].getNosVisitados()+" tamanho arvore = "+arvoreSBB_ordenada[i].getQuantidadeNiveisArvore());
        }
        //ArvoreSBB aleatoria
        ArvoreSBB[]arvoreSBB_aleatoria =new ArvoreSBB[10];
        Random gerador= new Random();
        for(int i=0;i<10;i++)
        {
            arvoreSBB_aleatoria[i]=new ArvoreSBB();
        }
        for(int i=0;i<10;i++)
        {
            int tamanho=10000*(i+1);
            for(int j=1;j<=tamanho;j++)
            {
               
                Item itens=new Item(gerador.nextInt(tamanho));
                //verifica se o numero aleatorio gerado ja esta contido na arvore
                while(arvoreSBB_aleatoria[i].pesquisa(itens)==true)
                {
                    //gerando um novo numero
                    itens=new Item(gerador.nextInt(tamanho));
                }
                arvoreSBB_aleatoria[i].insere(itens);
            }
        }
        System.out.println("");
        System.out.println("ARVORE ALEATORIA:");
        for(int i=0;i<10;i++)
        {
             arvoreSBB_aleatoria[i].pesquisa(aux);
             //pesquisando itens nao contido
             System.out.println("Arvore "+(i+1)+"  nó visitados = "+arvoreSBB_aleatoria[i].getNosVisitados()+" tamanho arvore = "+arvoreSBB_aleatoria[i].getQuantidadeNiveisArvore());
        }
        //ArvoreSBB aleatoria N elementos
        ArvoreSBB []arvoreSBB_aleatoriaN =new ArvoreSBB[10];
        Random gerador2= new Random();
        for(int i=0;i<10;i++)
        {
            arvoreSBB_aleatoriaN[i]=new ArvoreSBB();
        }
        long tamanho2;
        int mult10=0,mult5=0;
        for(int i=0;i<10;i++)
        {
            if(i%2==0)
            {
                tamanho2=(long) (Math.pow(10,mult5)*5);
                mult5++;
            }
            else
            {
                tamanho2=(long) (Math.pow(10,mult10)*10);
                mult10++;
            }
            for(int j=0;j<tamanho2;j++)
            {
                 Item itens=new Item(gerador2.nextInt(500000));
                //verifica se o numero aleatorio gerado ja tem na arvore
                while(arvoreSBB_aleatoriaN[i].pesquisa(itens)==true)
                {
                    itens=new Item(gerador2.nextInt(500000));
                }
                arvoreSBB_aleatoriaN[i].insere(itens);
            }
        }
        System.out.println("");
        System.out.println("ARVORE ALEATORIA N elementos:");
        for(int i=0;i<10;i++)
        {
             arvoreSBB_aleatoriaN[i].pesquisa(aux);
             System.out.println("Arvore "+(i+1)+"  nó visitados = "+arvoreSBB_aleatoriaN[i].getNosVisitados()+" tamanho arvore = "+arvoreSBB_aleatoriaN[i].getQuantidadeNiveisArvore());
        }
    }
    
}
