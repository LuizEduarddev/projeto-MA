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

    useEffect(() => {
        if (!isValidId)
        {
            navigate('/error/page');
        }
    }, [])

    useEffect(() => {
        async function startMesa()
        {
            const clienteToken = localStorage.getItem('clienteToken');
            if (clienteToken)
            {
                setTokenUsuario(clienteToken);
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
                        .catch(error => {
                            const message = error.response.data.message;
                            if (message)
                            {
                                alert(message);
                            }
                            else{
                                alert('Houve um erro ao tentar requisitar os integrantes da mesa');
                            }
                        })
                    }   
                    else{
                        alert('Um erro ocorreu ao tentar buscar a mesa do cliente.');
                    }
                })
                .catch(error => {
                    const message = error.response.data.message;
                    if (message)
                    {
                        alert(message);
                    }
                    else{
                        alert('Houve um erro ao tentar requisitar a mesa do cliente');
                    }
                })
            }
            else{
                navigate('/login');
            }
        }

        startMesa();
    }, [integrantes])

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
                            .catch(error => {
                                const message = error.response.data.message
                                if (message)
                                {
                                    alert(message);
                                }
                                else{
                                    alert('Erro em "/check-out-cliente/"'+ '\n' + 'Erro com a requisicao ou com a URL.' + "\n" + error);
                                }
                            })
                        }
                    })
                })
                .catch(error => {
                    const message = error.response.data.message
                    if (message)
                    {
                        alert(message);
                    }
                    else{
                        alert('Erro em "/get-clientes-by-mesa-id/"' + '\n' + 'Erro com a requisicao ou com a URL.' + "\n" + error);
                    }
                })
            })
            .catch(error => {
                const message = error.response.data.message;
                if (message)
                {
                    alert(message);
                }
                else{
                    alert('Houve um erro ao tentar requisitar a mesa do cliente');
                }
            })
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
                                .catch(error => {
                                    const message = error.response.data.message
                                    if (message)
                                    {
                                        alert(message);
                                    }
                                    else{
                                        alert('Erro em "mesa/cliente/delete/"'+ '\n' + 'Erro com a requisicao ou com a URL.' + "\n" + error);
                                    }
                                })
                            }
                        })
                        .catch(error => {
                            const message = error.response.data.message
                            if (message)
                            {
                                alert(message);
                            }
                            else{
                                alert('Erro em "/check-out-cliente/"'+ '\n' + 'Erro com a requisicao ou com a URL.' + "\n" + error);
                            }
                        })
                    }
                })
            })
            .catch(error => {
                const message = error.response.data.message
                if (message)
                {
                    alert(message);
                }
                else{
                    alert('Erro em "/get-clientes-by-mesa-id/"' + '\n' + 'Erro com a requisicao ou com a URL.' + "\n" + error);
                }
            })
        } 
        else{
            logOffMesaREQUISITION();
        }
    }

    return (
        <div>
            <div>
                <h3>
                    <Link to = "/home">
                        <Home/>
                        | Ir para a página inicial
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

                </ul>
            </div>

            <hr></hr>
            
            <div>
                <h1>Total da mesa</h1>
            </div>
        </div>
    );
}