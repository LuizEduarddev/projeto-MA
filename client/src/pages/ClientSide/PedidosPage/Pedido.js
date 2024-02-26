import React, {useEffect, useState} from "react";
import {useNavigate, Link} from 'react-router-dom';
import api from "../../../services/api";

export default function Pedido()
{
    const [clienteLogado, setClienteLogado] = useState('');
    const [tokenClienteLogado, setTokenClienteLogado] = useState('');

    const [pedidosCliente, setPedidosClientes] = useState([]);
    const [pedidoAtivo, setPedidoAtivo] = useState(false);

    const navigate = useNavigate ();

    /*
    useEffect(() => {
        const tokenClienteAtivo = localStorage.getItem('clienteToken');
        if (tokenClienteAtivo)
        {
            api.post('http://localhost:8080/api/pedido/get-by-id-cliente/' + tokenClienteAtivo)
            .then(response => {
                setPedidoAtivo(true);
                setPedidosClientes(response.data)
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
    }, [])
    */

    function getAllPedidos(pedido)
    {
        let pedidosList = []
        pedido.map(pedidos => {
            pedidosList.push(pedidos)
        })
        setPedidosClientes(pedidosList);
    }
    
    useEffect(() => {
        async function getPedidos()
        {
            const tokenClienteAtivo = localStorage.getItem('clienteToken');
            if (tokenClienteAtivo)
            {
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
        }

        getPedidos()
    }, [pedidosCliente])
            

    useEffect(() => {
        async function getCliente()
        {
            const getCliente = localStorage.getItem('username');
            const getTokenCliente = localStorage.getItem('clienteToken')
            if (getCliente && getTokenCliente)
            {
                setTokenClienteLogado(getTokenCliente);
                setClienteLogado(getCliente);
            }
            else{
                alert('É necessário estar logado para acessar esta área, voltando para a página inicial.')
                navigate('/home')
            }
        }

        getCliente()
    }, [clienteLogado])

    function toItensPedidos(id)
    {
        navigate('/cliente/pedidos/itens/' + id);
    }

    return (
        <div>
            {pedidosCliente && pedidosCliente.length > 0 ? (
                <div>
                    <ul>
                        {pedidosCliente.map(pedido => (
                            <button onClick={() => toItensPedidos(pedido.idPedido)}>
                                <li key={pedido.idPedido}>
                                    <h3>Cliente: <strong>{clienteLogado}</strong></h3>
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
                                        {pedido.pedidoFinalizado ? (
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
                        Olá, <strong>{clienteLogado}</strong>, esta é a sua área de pedidos. Assim que um pedido for feito ele aparecerá aqui!
                    </h2>
                </div>
            )}
        </div>
    );
}