import React, {useEffect, useState} from "react";
import api from "../../../services/api";
import { useParams, useNavigate , Link} from "react-router-dom";
import { MdHome as Home} from "react-icons/md";

export default function Mesa() {
    const { id } = useParams();
    const navigate = useNavigate ();
    const atualUser = localStorage.getItem('username');
    const [integrantes, setIntegrantes] = useState([]);
    const [pedidos, setPedidos] = useState([]);
    const isValidId = parseInt(id) >= 1 && parseInt(id) <= 9;
    const token = localStorage.getItem('clienteToken');
    const mesaToken = localStorage.getItem('mesaToken');

    if (!isValidId)
    {
        navigate('/error/page');
    }

    useEffect(() => {
        api.post('http://localhost:8080/api/cliente/get-by-id/' + token)
        .then(response => {
            localStorage.setItem('mesaToken', id)
            alert('Bem-vindo(a) a mesa ' + id + ' ' + response.data.nomeCliente);
        })
        .catch(error => {
            alert('Voce precisa estar logado para entrar em uma mesa.\nRedirecionando para o login');
            localStorage.setItem('mesaToken', id);
            navigate('/login')
        })
        
    }, []);

    useEffect(() => {
        api.post('http://localhost:8080/api/mesa/cliente/get-clientes-by-mesa-id/' + id)
        .then(response => {
            setIntegrantes(response.data);
        })
        .catch(error => {
            const errorMessageApi = error.response.data.message
            if(!errorMessageApi)
            {
                alert(error)
            }
            else{
                alert(error.response.data.message);
            }
        });
    }, []);

    useEffect(() => {
        api.post('http://localhost:8080/api/mesa/cliente/get-clientes-by-mesa-id/' + id)
        .then(response => {
            setIntegrantes(response.data);
        })
        .catch(error => {
            const errorMessageApi = error.response.data.message
            if(!errorMessageApi)
            {
                alert(error)
            }
            else{
                alert(error.response.data.message);
            }
        });
    }, [integrantes]);

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
                <h1>Integrantes da mesa.</h1>
                <ul>
                    {integrantes.map(integrante => (
                        <li key={integrante.idClienteDTO}>
                            {integrante.nomeClienteDTO && integrante.nomeClienteDTO === atualUser ? (
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