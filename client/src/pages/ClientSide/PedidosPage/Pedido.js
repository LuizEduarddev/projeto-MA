import React, {useEffect, useState} from "react";
import {useNavigate, Link} from 'react-router-dom';
import api from "../../../services/api";
import { MdHome as Home} from "react-icons/md";

export default function Pedido()
{
    const [tokenClienteLogado, setTokenClienteLogado] = useState('');
    const [clienteUsername, setClienteUsername] = useState('');
    const [pedidosCliente, setPedidosClientes] = useState([]);
    const [pedidoAtivo, setPedidoAtivo] = useState(false);

    const navigate = useNavigate();

    function getAllPedidos(pedido)
    {
        let pedidosList = []
        pedido.map(pedidos => {
            pedidosList.push(pedidos)
        })
        setPedidosClientes(pedidosList);
    }

    async function getUsername(token)
    {
        api.post('http://localhost:8080/api/cliente/get-by-id/' + token)
        .then(responseUsername => {
            setClienteUsername(responseUsername.data.nomeCliente);
            return;
        })
        .catch(error => {
            const errorMessage = error.response.data.message;
            if (errorMessage)
            {
                alert(errorMessage)
            }
            else{
                alert('Erro de requisicao ou erro de URL.' + '\n' + error);
            }
        })
    }
    
    useEffect(() => {
        async function getPedidos()
        {
            const tokenClienteAtivo = localStorage.getItem('clienteToken');
            if (tokenClienteAtivo)
            {
                setTokenClienteLogado(tokenClienteAtivo);
                getUsername(tokenClienteAtivo);
                api.post('http://localhost:8080/api/pedido/get-by-id-cliente/' + tokenClienteAtivo)
                .then(response => {
                    if (response)
                    {
                        getAllPedidos(response.data)
                        setPedidoAtivo(true);
                    }
                    else{
                        setPedidoAtivo(false)
                    }
                })
                .catch(error => {
                    const errorMessage = error.response.data.message;
                    if (errorMessage)
                    {
                        alert(errorMessage)
                    }
                    else{
                        alert('Erro de requisicao ou erro de URL.' + '\n' + error);
                    }
                })
            }
            else{
                navigate('/login');
            }
        }

        getPedidos()
    }, [pedidosCliente])

    function toItensPedidos(id)
    {
        navigate('/cliente/pedidos/itens/' + id);
    }

    return (
        <div>
            {pedidosCliente && pedidosCliente.length > 0 ? (
                <div>
                    <div>
                        <button>
                        <Link to = "/home">
                            <Home/>
                            | Ir para a página inicial
                        </Link>
                        </button>
                    </div>
                    <ul>
                        {pedidosCliente.map(pedido => (
                            <button onClick={() => toItensPedidos(pedido.idPedido)}>
                                <li key={pedido.idPedido}>
                                    <h3>Cliente: <strong>{clienteUsername}</strong></h3>
                                    <h3>Mesa {pedido.idMesaPedido}</h3>
                                    <h3>
                                        {pedido.pedidoPronto ? (
                                            <>
                                                <h3>Status do pedido: <strong>pronto!</strong></h3>
                                            </>
                                        ) : (
                                            <>
                                                <h3>Status do pedido: <strong>em andamento.</strong></h3>
                                            </>
                                        )}
                                    </h3>
                                    <h3>Total do pedido R$<strong>{pedido.totalPedido}</strong></h3>
                                    <h3>
                                        {pedido.pedidoPago ? (
                                            <>
                                            O pedido foi finalizado e pago.
                                            </>
                                        ) : (
                                            <>
                                            O pedido ainda não foi pago e está em aberto.
                                            </>
                                        )}
                                    </h3>
                                </li>
                            </button>
                        ))}
                    </ul>
                </div>    
            ) : (
                <div>
                    <h2>
                        Olá, <strong>{clienteUsername}</strong>, esta é a sua área de pedidos. Assim que um pedido for feito ele aparecerá aqui!
                    </h2>
                </div>
            )}
        </div>
    );
}