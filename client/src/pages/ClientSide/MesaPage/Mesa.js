import React, {useEffect, useState} from "react";
import api from "../../../services/api";
import { useParams, useNavigate , Link} from "react-router-dom";

export default function Mesa() {
    const { id } = useParams();
    const navigate = useNavigate ();
    
    const atualUser = localStorage.getItem('username');

    const [integrantes, setIntegrantes] = useState([]);

    const isValidId = parseInt(id) >= 1 && parseInt(id) <= 9;
    const token = localStorage.getItem('token');

    useEffect(() => {
        api.post('http://localhost:8080/api/mesa/get-by-id/' + id)
        .then(response => {
            setIntegrantes(response.data.clientesMesa);
        })
        .catch(error => {
            alert(error);
        });
    }, []);

    useEffect(() => {
        async function getIntegrantes() {
            api.post('http://localhost:8080/api/mesa/get-by-id/' + id)
            .then(response => {
                setIntegrantes(response.data.clientesMesa);
            })
            .catch(error => {
                alert(error);
            });
        }
        
        getIntegrantes();

    }, [integrantes]);

    useEffect(() => {
        if (!isValidId)
        {
            navigate('/error/page');
        }

        if (token === null)
        {
            alert('Voce precisa estar logado para entrar em uma mesa.\nRedirecionando para o login')
            localStorage.setItem('mesaIdToken', id);
            navigate('/login')
        }
        else{
            api.post('http://localhost:8080/api/cliente/get-by-id/' + token)
            .then(response => {
                alert('Bem-vindo(a) a mesa ' + id + ' ' + response.data.nomeCliente);
            })
            .catch(error => {
                alert('Voce precisa estar logado para entrar em uma mesa.\nRedirecionando para o login');
                localStorage.setItem('mesaIdToken', id);
                navigate('/login')
            })
        }
    }, []);

    return (
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
    );
}