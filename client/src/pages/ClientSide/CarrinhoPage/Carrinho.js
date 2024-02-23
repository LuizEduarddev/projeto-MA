import React, {useState, useEffect} from "react";
import {useNavigate, Link} from 'react-router-dom';
import { FaCheck as Check} from "react-icons/fa";
import { MdOutlinePayments as Cash,  MdOutlineCleaningServices as Clean, MdHome as Home, MdTableBar as Table} from "react-icons/md";
import api from "../../../services/api";

export default function Carrinho()
{
    
    const [cart, setCart] = useState([]);
    const [total, setTotal] = useState(0);
    const navigate = useNavigate ();
    const pagamento = 1000;
    const URLmesa = '/mesa/' + localStorage.getItem('mesaToken')
    const mesaToken = localStorage.getItem('mesaToken')

    useEffect(() => {
        const savedCart = localStorage.getItem('cart');
        if (savedCart) 
        {
            const parsedCart = JSON.parse(savedCart);
            setCart(parsedCart);
        }
    }, [])

    useEffect(() => {
        const getCarrinho = localStorage.getItem('cart');
        if (getCarrinho)
        {
            const savedCart = JSON.parse(getCarrinho);
            let totalAtualizado = 0;
            savedCart.map(item => {
                totalAtualizado += item.valorTotalIten;
            })
            if (totalAtualizado <= 0)
            {
                setTotal(0);
            }
            else{
                setTotal(totalAtualizado);
            }
        }
        else{
            setTotal(0)
        }
    }, []);

    useEffect(() => {
        const getCarrinho = localStorage.getItem('cart');
        if (getCarrinho)
        {
            const savedCart = JSON.parse(getCarrinho);
            let totalAtualizado = 0;
            savedCart.map(item => {
                totalAtualizado += item.valorTotalIten;
            })
            if (totalAtualizado <= 0)
            {
                setTotal(0);
            }
            else{
                setTotal(totalAtualizado);
            }
        }
        else{
            setTotal(0)
        }
    }, [cart])

    const decreaseQuantity = (id) => {
        const updatedCart = cart.map(item => {
            if (item.idProduto === id && item.quantidade > 1) {
                let totalItem = item.valorTotalIten - item.valorUnitarioItem; 
                if (totalItem <= 0)
                {
                    totalItem = 0;
                }
                return { ...item, quantidade: item.quantidade - 1, valorTotalIten: totalItem};
            }
            return item;
        });
        setCart(updatedCart);
        localStorage.setItem('cart', JSON.stringify(updatedCart));
    };

    const increaseQuantity = (id) => {
        const updatedCart = cart.map(item => {
            if (item.idProduto === id) {
                console.log("Quantidade anterior" + item.quantidade);
                return { ...item, quantidade: item.quantidade + 1, valorTotalIten:  item.valorTotalIten + item.valorUnitarioItem};
            }
            return item;
        });
        setCart(updatedCart);
        localStorage.setItem('cart', JSON.stringify(updatedCart));
    };

    function deleteItenById(id)
    {
        let cartItens = JSON.parse(localStorage.getItem('cart'));
        cartItens = cartItens.filter(item => item.idProduto !== id);
        localStorage.setItem('cart', JSON.stringify(cartItens));
        setCart([...cartItens]);
    }

    function CleanCart()
    {
        const cleanCart = [];
        localStorage.setItem('cart', cleanCart);
        setCart([...cleanCart]);
        setTotal(0);
    }

    async function senOrder(e)
    {
        e.preventDefault();
        
        const getCarrinho = localStorage.getItem('cart');
        const mesaToken = localStorage.getItem('mesaToken');
        const clienteToken = localStorage.getItem('clienteToken')
        if (!clienteToken || !mesaToken)
        {
            if (!clienteToken)
            {
                alert('Voce precisa estar logado para fazer esta operacao.' + "\nDirecionando para o Login");
                navigate("/login")
            }
            else{
                if (!mesaToken)
                {
                    const nomeUsuario = localStorage.getItem('username');
                    if (nomeUsuario)
                    {
                        alert(nomeUsuario + ", é necessário estar em uma mesa para poder efetuar um pedido")
                    }
                    else{
                        alert("Prezado, é necessário estar em uma mesa para poder efetuar um pedido")
                    }
                }
            }
        }
        else{
            if (getCarrinho)
            {
                const postPedido = {
                    "idMesaPedido": mesaToken,
                    "idClientePedido": clienteToken,
                    "totalPedido": total
                }
                
                postarItemPedido(postPedido)  
            }
        }
    }
    
    async function postarItemPedido(postPedido)
    {
        const getCarrinho = localStorage.getItem('cart')
        api.post('http://localhost:8080/api/pedido/post', postPedido)
        .then(responseIdPedido => {
            const idPedido = responseIdPedido.data.idPedido;
            if (getCarrinho)
            {
                const carrinho = JSON.parse(getCarrinho);
                carrinho.map(item => {
                    const idProduto = item.idProduto
                    const itemDTO = {
                        "quantidadeProduto": item.quantidade
                    }
                    api.post('http://localhost:8080/api/pedido/item-pedido/post/' + idPedido + '/' + idProduto, itemDTO)
                    .then(response => {
                        alert('Pedido realizado com sucesso, direcionado para pedidos....')
                        CleanCart()
                        navigate('/cliente/pedidos')
                    })
                    .catch(error => {
                        const errorMessage = error.response.data.message
                        if (errorMessage)
                        {
                            alert(errorMessage)
                        }
                        else{
                            alert(error)
                        }
                    })
                })
            }  
        })
        .catch(error => {
            const errorMessage = error.response.data.message
            if (errorMessage)
            {
                alert(errorMessage)
            }
            else{
                alert(error)
            }
        })
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
            <div>
                {total === 0 ? (
                    <div>
                    </div>
                ) : (
                    <div>
                        <div>
                            <button onClick={CleanCart}>
                                <h3>
                                    <Clean/>
                                    | Limpar Carrinho
                                </h3>
                            </button>
                        </div>

                        <div>
                            <button>
                                <h3>
                                    <Link to = "/home">
                                        <Home/>
                                        | Ir para a página inicial
                                    </Link>
                                </h3>
                            </button>
                        </div>

                        <div>    
                            {mesaToken ? (
                                <button>
                                    <h3>
                                        <Link to = {URLmesa}>
                                            <Table/>
                                            | Ver a mesa
                                        </Link>
                                    </h3>
                                </button>
                            ) : (
                                <div>
                                    
                                </div>
                            )
                        }
                        </div>
                        
                    </div>
                )
            }
            </div>
            <ul>
                {cart.map(item => (
                        <li key={item.idProduto}>
                            <div>
                                <h1>{item.nomeProduto}</h1>
                                <div>
                                    <h1>quantidade: 
                                        <button onClick={() => decreaseQuantity(item.idProduto)}>-</button> 
                                        {item.quantidade} 
                                        <button onClick={() => increaseQuantity(item.idProduto)}>+</button>
                                    </h1>
                                </div>
                                <h1>valor: {item.valorTotalIten.toFixed(2)}</h1>
                                <button onClick={() => deleteItenById(item.idProduto)}>
                                    <h3>Remover item</h3>
                                </button>
                            </div>
                            <hr />
                        </li>
                    ))
                }
            </ul>
            <hr/>
            <h2>
            {total === 0 ? (
                    <div>
                        <h3>Nada por enquanto...</h3>
                        <h3>Adicione produtos ao carrinho para aparecerem aqui!</h3>
                        <h4><Link to = "/home">Voltar para a página inicial.</Link></h4>
                    </div>
                ) : (
                    <div>
                        Total do pedido: {total.toFixed(2) || null}
                    </div>
                )}
            </h2>
            <hr/>
            <div>
                {total === 0 ? (
                    <div>

                    </div>
                    ) : (
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
                        )
                }
            </div>
        </div>
    );
}