import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';
import api from "../../../services/api";

export default function Login()
{
    const [cpfDTO, setCpfDTO] = useState('');
    const [senhaDTO, setSenhaDTO] = useState('');
    const navigate = useNavigate ();
    const mesaToken = localStorage.getItem('mesaToken')
    const clienteToken = localStorage.getItem('clienteToken')

    async function tryLogin(e)
    {
        e.preventDefault();

        if (clienteToken)
        {
            alert('Voce ja está logado')
            if (!mesaToken)
            {
                navigate("/promo")
            }
            else{
                navigate("/mesa/" + mesaToken)
            }
        }
        else{
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
                .then(responseCliente => {
                    if (mesaToken != null && mesaToken < 1 || mesaToken > 9)
                    {
                        alert('mesa nao registrada no sistema')
                    }
                    else{
                        if (mesaToken == null)
                        {
                            navigate("/promo")
                        }
                        else{
                            const clienteMesa = {
                                "idCliente": responseCliente.data.idClienteDTO,
                                "idMesa": mesaToken
                            }
                            api.post('http://localhost:8080/api/mesa/cliente/add', clienteMesa)
                            .then(() => {
                                localStorage.setItem('username', responseCliente.data.nomeClienteDTO);
                                localStorage.setItem('clienteToken', responseCliente.data.idClienteDTO);
                                navigate('/mesa/' + mesaToken);
                            })
                            .catch(error => {
                                alert(error.response.data.message)
                            })
                        }
                    }
                })
                .catch(error => {
                    alert(error.response.data.message);
                })
            }
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