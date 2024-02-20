import React, {useEffect, useState} from "react";
import api from "../../../services/api";
import { useParams, useNavigate , Link} from "react-router-dom";

export default function Mesa() {
    const { id } = useParams();
    const navigate = useNavigate ();
    
    const isValidId = parseInt(id) >= 1 && parseInt(id) <= 9;
    const token = localStorage.getItem('token');

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
            <h2>Mesa {id}</h2>
            <p>id no momento {token}</p>
        </div>
    );
}