package lista2 ;
public class Item 
{
    private long chave ;
    public Item ( long chave ) 
    {
        this . chave = chave ;
    }
    public long compara ( Item it ) 
    {
        Item item = it ;
        if ( this . chave < item . chave )
            return -1;
        else if ( this . chave > item . chave )
            return 1;
        return 0;
    }
    public long getChave () 
    {
        return this.chave ;
    }
}
