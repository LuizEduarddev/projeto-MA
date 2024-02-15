import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';
import api from "../../../services/api";

export default function Login()
{
    const [cpfDTO, setCpfDTO] = useState('');
    const [senhaDTO, setSenhaDTO] = useState('');
    const navigate = useNavigate ();

    async function tryLogin(e)
    {
        e.preventDefault();

        if (!cpfDTO || !senhaDTO)
        {
            alert('Por favor, preencha todos os campos.');
            return;
        }
        else
        {
            const credentials = {
                cpfDTO,
                senhaDTO
            }
    
            api.post('http://localhost:8080/api/cliente/check-in', credentials)
            .then(response => {
                localStorage.setItem('username', response.data.nomeClienteDTO);
                localStorage.setItem('token', response.data.idClienteDTO);
                navigate('/promo');
            })
            .catch(error => {
                alert(error.response.data.message);
            })
        }
    }


    return (
        <div>
            <form onSubmit={tryLogin}>
                <h1>Bem-vindo(a)!<br/>
                    Digite suas credenciais para acessar o portal <b>Maria Amelia Doces!</b>
                </h1>
                <input 
                placeholder="CPF"
                value={cpfDTO}
                onChange={e => setCpfDTO(e.target.value)}/>
                
                <input 
                placeholder="senha" 
                type="password"
                value={senhaDTO}
                onChange={e => setSenhaDTO(e.target.value)}/>
                
                <button type="submit">
                    Entrar
                </button>
            </form>
        </div>
    );
}