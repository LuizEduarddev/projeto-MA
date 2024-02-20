import React, {useState, useEffect} from "react";
import {useNavigate} from 'react-router-dom';
import { CiCircleRemove as Remove} from "react-icons/ci";
import { FaCheck as Check} from "react-icons/fa";
import { MdOutlinePayments as Cash} from "react-icons/md";
import api from "../../../services/api";

export default function Carrinho()
{
    
    const [cart, setCart] = useState([]);
    const [total, setTotal] = useState(0);
    const pagamento = 1000;

    useEffect(() => {
        const savedCart = localStorage.getItem('cart');
        if (savedCart) {
            const parsedCart = JSON.parse(savedCart);
            let valorTotalConta = 0; 
            setCart(parsedCart);
            parsedCart.forEach(iten => {
                valorTotalConta += iten.valorTotalIten;
            })
            setTotal(valorTotalConta);
        }
    }, []);

    function deleteItenById(id)
    {
        let cartItens = JSON.parse(localStorage.getItem('cart'));
        cartItens = cartItens.filter(item => item.idProduto !== id);
        localStorage.setItem('cart', JSON.stringify(cartItens));
        setCart([...cartItens]);
    }

    async function senOrder(e)
    {
        e.preventDefault();
        const idPedido = 2;
        let tryGetCart = localStorage.getItem('cart')
        if (tryGetCart)
        {
            let cartItens = JSON.parse(tryGetCart);
            cartItens.push({
                "idPedido": idPedido 
            })
            
            localStorage.setItem('cart', cartItens);
            setCart([...cartItens]);
            console.log(cartItens);
        }
        else{
            alert('O carrinho está vazio.');
        }

    }

    async function payOrder(e)
    {
        e.preventDefault();

        if (pagamento >= total)
        {
            const cleanCart = [];
            localStorage.setItem('cart', cleanCart);
            setCart([...cleanCart]);
            alert('Pagamento realizado com sucesso!');
            setTotal(0);
        }
        else if (pagamento < total)
        {
            alert('Saldo insuficiente.');
        }
    }

    return(
        <div>
            <ul>
                {cart.map(itens =>
                    <li key={itens.idProduto}>
                        <div>
                            <h1>{itens.nomeProduto}</h1>
                            <h1>quantidade: {itens.quantidade}</h1>
                            <h1>valor: {itens.valorTotalIten.toFixed(2) || null}</h1>
                            <button onClick={() => deleteItenById(itens.idProduto)}>
                                <h3>
                                    <Remove/>
                                    | Remover item
                                </h3>
                            </button>
                        </div>
                        <hr/>
                    </li>
                )}
            </ul>
            <hr/>
            <h2>Total do pedido: {total.toFixed(2) || null}</h2>
            <hr/>
            <div>
                <button onClick={senOrder}>
                    <h3>    
                        <Check/>
                        | Realizar pedido
                    </h3>
                </button>
                <button onClick={payOrder}>
                <h3>    
                        <Cash/>
                        | Realizar pagamento
                    </h3>
                </button>
                <hr/>
            </div>
        </div>
    );
}