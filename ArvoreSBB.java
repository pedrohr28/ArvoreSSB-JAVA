
package lista2;
public class ArvoreSBB 
{
    private No raiz;
    private long nosVisitados;
    private int quantidadeNiveisArvore;
    private static final byte Horizontal = 0;
    private static final byte Vertical = 1;
    private boolean propSBB;
    private int tamanhoArvore;
    private boolean primeiraFolha;
    
    private static class No 
    {
        Item reg ;
        No esq , dir ;
        byte incE , incD ;
    } 
    
    //inicialização dos atributos da classe
    public ArvoreSBB()
    {
        this.raiz=null;
        this.propSBB=true;
        this.nosVisitados=0;
        this.quantidadeNiveisArvore=0;
        this.tamanhoArvore=0;
    }
    
    private No insere(Item reg, No pai, No filho, boolean filhoEsq)
    {
       //insere uma chave na arvore e balanceia-a
       
        if (filho == null) //verifica se o nó é o ultimo filho 
        {
            filho = new No();
            filho.reg = reg;
            filho.incE = Vertical;
            filho.incD = Vertical;
            filho.esq = null;
            filho.dir = null;
            if (pai != null) {
                if (filhoEsq) {
                    pai.incE = Horizontal;
                } else {
                    pai.incD = Horizontal;
                }
            }
            this.propSBB = false;
        } 
        else if (reg.compara(filho.reg) < 0) 
        {
            filho.esq = insere(reg, filho, filho.esq, true);
            if (!this.propSBB) 
            {
                if (filho.incE == Horizontal)
                {
                    if (filho.esq.incE == Horizontal)
                    {
                        filho = this.ee(filho); // trasformacao esquerda-esquerda
                        if (pai != null) 
                        {
                            if (filhoEsq) 
                            {
                                pai.incE = Horizontal;
                            } 
                            else
                            {
                                pai.incD = Horizontal;
                            }
                        }
                    } 
                    else if (filho.esq.incD == Horizontal) 
                    {
                        filho = this.ed(filho); // trasformacao esquerda-direita
                        if (pai != null) 
                        {
                            if (filhoEsq) 
                            {
                                pai.incE = Horizontal;
                            } 
                            else 
                            {
                                pai.incD = Horizontal;
                            }
                        }
                    }
                } 
                else 
                {
                    this.propSBB = true;
                }
            }
        } 
        else if (reg.compara(filho.reg) > 0) 
        {
            filho.dir = insere(reg, filho, filho.dir, false);
            if (!this.propSBB) 
            {
                if (filho.incD == Horizontal) 
                {
                    if (filho.dir.incD == Horizontal) 
                    {
                        filho = this.dd(filho); //  trasformacao direita-direita
                        if (pai != null) 
                        {
                            if (filhoEsq) 
                            {
                                pai.incE = Horizontal;
                            } 
                            else 
                            {
                                pai.incD = Horizontal;
                            }
                        }
                    } 
                    else if (filho.dir.incE == Horizontal) 
                    {
                        filho = this.de(filho); //trasformacao direita-esquerda
                        if (pai != null) 
                        {
                            if (filhoEsq) 
                            {
                                pai.incE = Horizontal;
                            } 
                            else 
                            {
                                pai.incD = Horizontal;
                            }
                        }
                    }
                } 
                else 
                {
                    this.propSBB = true;
                }
            }
        } 
        else 
        {
            System.out.println("Erro: Registro ja existente");
            this.propSBB = true;
        }
        return filho;
    }
    
    private void calculaTamanho(No p,int nivel) //calcula a quantidade de niveis da arvoresbb
    {
        if (p==null) 
        {
            return ;
        }
        if (this.primeiraFolha) 
        {
            if (this.tamanhoArvore<nivel ) 
            {
                this.tamanhoArvore=nivel ;
            }
        }
        if (p.esq==null&&p.dir==null) 
        {
            if (this.primeiraFolha) 
            {
                this.primeiraFolha=false ;
            }
        }
        if (p.incE==Horizontal) 
        {
            this.calculaTamanho(p.esq ,nivel) ;
        }
        else 
        {
            this.calculaTamanho(p.esq ,nivel+1) ;
        }
        if (p.incD==Horizontal) 
        {
            this.calculaTamanho(p.dir,nivel) ;
        } 
        else 
        {
            this.calculaTamanho(p.dir,nivel+1) ;
        }
    }
    
    public void retornaQuantidadeNiveis ()//metodo que calcula a quantidade de niveis da arvore por recursividade
    {
        this.tamanhoArvore = 0;
        this.primeiraFolha = true ;
        this.calculaTamanho(this.raiz,1) ;
    }
    
    public boolean pesquisa(Item reg)//pesquisa um item dado uma chave
    {
        this.nosVisitados=0;
        return this.pesquisa(reg,this.raiz)!=null;
    }
    private Item pesquisa(Item reg, No p)
    {
        if (p == null) 
        {
            return null; // Registro nao econtrado
        } 
        //confere se a chave do item esta a esquerda ou direira do nó
        //esquerda menor que zero e direita maior zero
        //esquerda, chave menor que o nó e direita chave maior que o nó
        else if (reg.compara(p.reg) < 0) 
        {
            this.nosVisitados++;
            return pesquisa(reg, p.esq);//"descendo" o lado esquerdo da arvore
        } 
        else if (reg.compara(p.reg) > 0) 
        {
            this.nosVisitados++;
            return pesquisa(reg, p.dir);////"descendo" o lado direito da arvore
        } 
        else
        {
            return p.reg;
        }
    }
    public void insere(Item reg)
    {
        //inicializa o processo de inserção de uma chave a partir do nó raiz
        this.raiz=insere(reg,null,this.raiz,true);
    }
    public long getNosVisitados()//retorna a quantidades de comparações ou nós visitados a partir de uma busca
    {
        return this.nosVisitados;
    }
    public int getQuantidadeNiveisArvore()//retorna a quantidade de niveis da arvoresbb
    {
        retornaQuantidadeNiveis();
        return this.tamanhoArvore;
    }
    private No ee ( No ap ) //transformação esquerda para esquerda
    {
        No ap1 = ap . esq ;
        ap . esq = ap1 . dir ;
        ap1 . dir = ap ;
        ap1 . incE = Vertical ;
        ap . incE = Vertical ;
        ap = ap1 ;
        return ap ;
    }
    private No ed ( No ap ) //transformação esquerda para direita
    {
        No ap1 = ap . esq ;
        No ap2 = ap1 . dir ;
        ap1 . incD = Vertical ;
        ap . incE = Vertical ;
        ap1 . dir = ap2 . esq ;
        ap2 . esq = ap1 ;
        ap . esq = ap2 . dir ;
        ap2 . dir = ap ;
        ap = ap2 ;
        return ap ;
    }
    private No dd ( No ap ) //transformação direita para direita
    {
        No ap1 = ap . dir ;
        ap . dir = ap1 . esq ;
        ap1 . esq = ap ;
        ap1 . incD = Vertical ;
        ap . incD = Vertical ;
        ap = ap1 ;
        return ap ;
    }
    private No de ( No ap ) //transformação direita para esquerda
    {
        No ap1 = ap . dir ;
        No ap2 = ap1 . esq ;
        ap1 . incE = Vertical ;
        ap . incD = Vertical ;
        ap1 . esq = ap2 . dir ;
        ap2 . dir = ap1 ;
        ap . dir = ap2 . esq ;
        ap2 . esq = ap ;
        ap = ap2 ;
        return ap ;
    }

}
