import React, {useState, useEffect} from "react";
import {useNavigate} from 'react-router-dom';
import { CiCircleRemove as Remove} from "react-icons/ci";
import { FaCheck as Check} from "react-icons/fa";
import { MdOutlinePayments as Cash} from "react-icons/md";
import api from "../../../services/api";

export default function Carrinho()
{
    
    const [cart, setCart] = useState([]);

    useEffect(() => {
        const savedCart = localStorage.getItem('cart');
        if (savedCart) {
            const parsedCart = JSON.parse(savedCart);
            setCart(parsedCart);
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

        api.post('')
    }

    async function payOrder(e)
    {
        e.preventDefault();

        const cleanCart = [];
        localStorage.setItem('cart', cleanCart);
        setCart([...cleanCart]);
        alert('Pagamento realizado com sucesso!');
    }

    return(
        <div>
            <ul>
                {cart.map(itens =>
                    <li key={itens.idProduto}>
                        <div>
                            <h1>id: {itens.idProduto}</h1>
                            <h1>quantidade: {itens.quantidade}</h1>
                            <button onClick={() => deleteItenById(itens.idProduto)}>
                                <h3>
                                    <Remove/>
                                    | Remover item
                                </h3>
                            </button>
                        </div>
                    </li>
                )} || 
                <h1>O carrinho está vazio</h1>
            </ul>
            <button onClick={senOrder}>
                <h3>    
                    <Check/>
                    | Finalizar pedido
                </h3>
            </button>
            <button onClick={payOrder}>
            <h3>    
                    <Cash/>
                    | Realizar pagamento
                </h3>
            </button>
        </div>
    );
}