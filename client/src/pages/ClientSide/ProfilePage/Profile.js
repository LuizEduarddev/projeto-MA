import React, {useEffect, useState} from "react";
import {useNavigate, Link} from 'react-router-dom';
import api from "../../../services/api";

export default function Profile()
{
    const [cpfCliente, setCpfCliente] = useState('');
    const [enderecoCliente, setEnderecoCliente] = useState('');
    const [idadeCliente, setIdadeCliente] = useState('');
    const [numeroCliente, setNumeroCliente] = useState('');
    const [senhaCliente, setSenhaCliente] = useState('');
    const [aniversarioCliente, setAniversarioCliente] = useState('');
    const [nomeCliente, setNomeCliente] = useState('');

    useEffect(() => {
        async function fetchData() {
            try {
                const token = localStorage.getItem('clienteToken')
                const response = await api.post('http://localhost:8080/api/cliente/get-by-id/' + token);
                setCpfCliente(response.data.cpfCliente);
                setEnderecoCliente(response.data.enderecoCliente);
                setIdadeCliente(response.data.idadeCliente);
                setNumeroCliente(response.data.numeroCliente);
                setSenhaCliente(response.data.senhaCliente);
                setAniversarioCliente(response.data.aniversarioCliente);
                setNomeCliente(response.data.nomeCliente);
            } catch (error) {
                alert(error);
            }
        }
    
        fetchData();
    }, []);

    async function updateData(e)
    {
        e.preventDefault();

        const token = localStorage.getItem('clienteToken');
        let cliente = {
            cpfCliente,
            numeroCliente,
            enderecoCliente,
            nomeCliente,
            aniversarioCliente,
            senhaCliente,
            idadeCliente
        }

        api.put('http://localhost:8080/api/cliente/alter/' + token, cliente)
        .then(response => {
            localStorage.setItem('username', nomeCliente);
            alert('Cliente atualizado com sucesso.');
        })
        .catch(error => {
            alert(error.message);
        })
    }

    return (
        <div>
            <h1>Perfil de {nomeCliente}</h1>
            <div>
                <ul>
                    <li>nome: <strong>{nomeCliente}</strong></li>
                    <li>endereco: <strong>{enderecoCliente}</strong></li>
                    <li>data de aniversario: <strong>{aniversarioCliente}</strong></li>
                    <li>idade: <strong>{idadeCliente}</strong></li>
                    <li>numero de telefone: <strong>{numeroCliente}</strong></li>
                    <li>cpf: <strong>{cpfCliente}</strong></li>
                    <li>senha: <strong>{senhaCliente}</strong></li>
                </ul>
            </div>
            <div>
                <p>dados básicos</p>
                <form onSubmit={updateData}>
                    <input 
                        placeholder={cpfCliente} 
                        onChange={e => setCpfCliente(e.target.value)}/>

                    <input 
                        placeholder={enderecoCliente} 
                        onChange={e => setEnderecoCliente(e.target.value)}/>

                    <input 
                        placeholder={localStorage.getItem('username')}
                        onChange={e => setNomeCliente(e.target.value)}/>

                    <input 
                        placeholder={numeroCliente} 
                        onChange={e => setNumeroCliente(e.target.value)}/>

                    <input 
                        placeholder={'idade ' + idadeCliente} 
                        onChange={e => setIdadeCliente(e.target.value)}
                    />

                    <input 
                        placeholder={senhaCliente} 
                        type="password"
                        value={senhaCliente}
                        onChange={e => setSenhaCliente(e.target.value)}/>

                    <input 
                        placeholder={aniversarioCliente}
                        onChange={e => setAniversarioCliente(e.target.value)}/>
                    <button type="submit">atualizar informacoes</button>
                </form>

                <Link to='/home'>Voltar para a página inicial</Link>
            </div>
        </div>
    );
}