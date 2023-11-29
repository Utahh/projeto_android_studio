import com.example.projetobeta.Cliente.Cliente;
import com.example.projetobeta.DbHelper;
import com.example.projetobeta.Item.ItemVenda;
import com.example.projetobeta.Produto.Produto;
import com.example.projetobeta.Venda.VendaContract;

import java.util.ArrayList;
import java.util.List;

public class Venda {

    private long idVenda;
    private Cliente cliente;
    private List<ItemVenda> itensVenda;

    public Venda(Cliente cliente) {
        this.idVenda = 0;
        this.cliente = cliente;
        this.itensVenda = new ArrayList<>();
    }

    public void adicionarItemVenda(Produto produto, int quantidade) {
        ItemVenda item = new ItemVenda(produto, quantidade);
        itensVenda.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemVenda item : itensVenda) {
            total += item.getSubtotal();
        }
        return total;
    }


}
