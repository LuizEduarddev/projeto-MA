import React, {useEffect, useState} from "react";
import api from "../../../services/api";
import { useParams, useNavigate , Link} from "react-router-dom";
import { MdHome as Home} from "react-icons/md";
import { IoIosCloseCircleOutline as Out} from "react-icons/io";

export default function Mesa() {
    const { id } = useParams();
    const navigate = useNavigate ();
    const isValidId = parseInt(id) >= 1 && parseInt(id) <= 9;
    const [integrantes, setIntegrantes] = useState([]);
    const [tokenUsuario, setTokenUsuario] = useState('');
    const [tokenMesa, setTokenMesa] = useState('');
    const [pedidosCliente, setPedidosCliente] = useState([]);

    useEffect(() => {
        if (!isValidId)
        {
            navigate('/error/page');
        }
    }, [])

    async function getResponseMesa(clienteToken)
    {
        api.post('http://localhost:8080/api/mesa/cliente/get-by-cliente-id/' + clienteToken)
        .then(responseIdMesa => {
            const idMesa = responseIdMesa.data.idMesa;
            if (idMesa)
            {
                setTokenMesa(idMesa);
                api.post('http://localhost:8080/api/mesa/cliente/get-clientes-by-mesa-id/' + idMesa)
                .then(responseIntegrantes => {
                    setIntegrantes(responseIntegrantes.data);
                })
                .catch(error => {getErrorMessage(error)})
            }   
            else{
                alert('Um erro ocorreu ao tentar buscar a mesa do cliente.');
            }
        })
        .catch(error => {getErrorMessage(error)})
    }

    function getErrorMessage(error)
    {
        const message = error.response.data.message;
        if (message)
        {
            alert(message);
        }
        else{
            alert('ERRO DE REQUISICAO OU/DE URL.');
        }
    }

    async function getPedidosByMesa(clienteToken)
    {
        api.post('http://localhost:8080/api/mesa/cliente/get-by-cliente-id/' + clienteToken)
        .then(responseIdMesa => {
            const idMesa = responseIdMesa.data.idMesa;
            if (idMesa)
            {
                setTokenMesa(idMesa);    
                api.post('http://localhost:8080/api/pedido/get-by-mesa-id/' + idMesa)
                .then(async responsePedidos => {
                    setPedidosCliente(responsePedidos.data);
                    console.log(responsePedidos.data)
                })  
                .catch(erro => {getErrorMessage(erro)})                  
            }   
            else{
                alert('Um erro ocorreu ao tentar buscar a mesa do cliente.');
            }
        })
        .catch(error => {getErrorMessage(error)})
    }

    useEffect(() => {
        async function startMesa()
        {
            const clienteToken = localStorage.getItem('clienteToken');
            if (clienteToken)
            {
                setTokenUsuario(clienteToken);
                getResponseMesa(clienteToken);
                getPedidosByMesa(clienteToken);
            }
            else{
                navigate('/login');
            }
        }

        startMesa();
    }, [integrantes])

    function toItensPedidos(id)
    {
        navigate('/cliente/pedidos/itens/' + id);
    }

    async function logOffMesaREQUISITION()
    {
        const clienteToken = localStorage.getItem('clienteToken');
        if (clienteToken)
        {
            api.post('http://localhost:8080/api/mesa/cliente/get-by-cliente-id/' + clienteToken)
            .then(responseIdMesa => {
               const mesaToken = responseIdMesa.data.idMesa;
               api.post('http://localhost:8080/api/mesa/cliente/get-clientes-by-mesa-id/' + mesaToken)
                .then(responseUsuario => {
                    const listMesa = responseUsuario.data;
                    listMesa.map(clientes => {
                        if (clientes.idClienteDTO == tokenUsuario)
                        {
                            api.post('http://localhost:8080/api/pedido/check-out-cliente/' + mesaToken + "/" + tokenUsuario)
                            .then(responseCheckOut => {
                                if (responseCheckOut)
                                {
                                    alert('Voce ainda tem pagamentos de pedidos pendentes. Pague todos para poder sair da mesa.');
                                }
                                else{
                                    alert('Saindo da mesa....');
        
                                }
                            })
                            .catch(error => {getErrorMessage(error)})
                        }
                    })
                })
                .catch(error => {getErrorMessage(error)})
            })
            .catch(error => {getErrorMessage(error)})
        }
        else{
            alert('Para esta funcao é necessário estar logado.');
            navigate('/login');
        }
    }

    async function logOffMesa()
    {
        const mesaToken = localStorage.getItem('mesaToken')
        const tokenUsuario = localStorage.getItem('clienteToken');
        if (mesaToken && tokenUsuario)
        {
            api.post('http://localhost:8080/api/mesa/cliente/get-clientes-by-mesa-id/' + mesaToken)
            .then(responseUsuario => {
                const listMesa = responseUsuario.data;
                listMesa.map(clientes => {
                    if (clientes.idClienteDTO == tokenUsuario)
                    {
                        api.post('http://localhost:8080/api/pedido/check-out-cliente/' + mesaToken + "/" + tokenUsuario)
                        .then(responseCheckOut => {
                            if (responseCheckOut.data)
                            {
                                alert('Voce ainda tem pagamentos de pedidos pendentes. Pague todos para poder sair da mesa.');
                            }
                            else{
                                alert('Saindo da mesa....');
                                api.delete('http://localhost:8080/api/mesa/cliente/delete/' + tokenUsuario + '/' + mesaToken)
                                .then(responseCheckOut => {
                                    localStorage.removeItem('mesaToken');
                                    navigate('/home')
                                })
                                .catch(error => {getErrorMessage(error)})
                            }
                        })
                        .catch(error => {getErrorMessage(error)})
                    }
                })
            })
            .catch(error => {getErrorMessage(error)})
        } 
        else{
            logOffMesaREQUISITION();
        }
    }

    async function getUserName(idCliente)
    {
        api.post('http://localhost:8080/api/cliente/get-by-id/' + idCliente)
        .then(response => {
            return response.data.nomeCliente;
        })
        .catch(error => {getErrorMessage(error)})
    }

    return (
        <div>
            <div>
                <h3>
                    <Link to = "/home">
                        <Home/>
                        | Ir para a página inicial SR LUIZ QUE TAL COMECAR DO ZERO ?
                    </Link>
                </h3>
            </div>

            <div>
                <button onClick={() => logOffMesa()}>
                    <Out/>
                    | Sair da mesa
                </button>
            </div>

            <div>
                <h1>Integrantes da mesa.</h1>
                <ul>
                    {integrantes.map(integrante => (
                        <li key={integrante.idClienteDTO}>
                            {integrante.idClienteDTO && integrante.idClienteDTO === tokenUsuario ? (
                                <>
                                    <h1>{integrante.nomeClienteDTO} (Você)</h1>
                                </>
                            ) : (
                                <h1>{integrante.nomeClienteDTO}</h1>
                            )}
                        </li>
                    ))}
                </ul>
            </div>    
            <hr></hr>
            <div>
                <h1>Pedidos da mesa</h1>
                <ul>
                    {pedidosCliente.map(pedido => (
                        <button onClick={() => toItensPedidos(pedido.idPedido)}>
                        <li key={pedido.idPedido}>
                                <h3>Cliente: <strong>{getUserName(pedido.idClientePedido)}</strong></h3>
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
        </div>
    );
}