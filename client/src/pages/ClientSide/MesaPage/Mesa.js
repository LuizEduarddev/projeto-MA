import React, {useEffect, useState} from "react";
import api from "../../../services/api";
import { useParams, useNavigate , Link} from "react-router-dom";
import { MdHome as Home} from "react-icons/md";
import { IoIosCloseCircleOutline as Out} from "react-icons/io";

export default function Mesa() {
    const { id } = useParams();
    const navigate = useNavigate ();
    const [nomeUsuario, setNomeUsuario] = useState('');
    const [integrantes, setIntegrantes] = useState([]);
    const [pedidos, setPedidos] = useState([]);
    const [mesaToken, setMesaToken] = useState('');
    const isValidId = parseInt(id) >= 1 && parseInt(id) <= 9;
    const [isUsuarioAtivo, setIsUsuarioAtivo] = useState(false);
    const [tokenUsuario, setTokenUsuario] = useState('');

    if (!isValidId)
    {
        navigate('/error/page');
    }

    useEffect(() => {
        const mesa = localStorage.getItem('mesaToken');
        if (mesa === null)
        {
            alert('Mesa fora de conexao. Voltando para a página inicial.')
            navigate('/home')
        }
        else{
            setMesaToken(mesa);
        }
    }, [mesaToken])

    useEffect(() => {
        const tokenUsuario = localStorage.getItem('clienteToken');
        const nomeUser = localStorage.getItem('username');

        if (tokenUsuario && nomeUser)
        {
            setIsUsuarioAtivo(true);
            setTokenUsuario(tokenUsuario);
            setNomeUsuario(nomeUser);
        }
        else if (!tokenUsuario){
            alert('Voce precisar estar logado para esta operacao. Direcionando para o login');
            localStorage.setItem('mesaToken', id);
            navigate('/login')
        }
        else if (!nomeUser){
            alert('Um erro ocorreu ao tentar buscar o seu nome, logue e tente novamente.' + "\nRedirecionando para o login");
            localStorage.setItem('mesaToken', id);
            navigate('/login')
        }
        
    }, [isUsuarioAtivo]);

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

    async function logOffMesa()
    {
        api.post('http://localhost:8080/api/mesa/cliente/get-clientes-by-mesa-id/' + mesaToken)
        .then(responseUsuario => {
            const listMesa = responseUsuario.data;
            listMesa.map(clientes => {
                if (clientes.idClienteDTO === tokenUsuario && clientes.nomeClienteDTO === nomeUsuario)
                {
                    alert('entrou na condicao')
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
                else{
                    console.log("id cliente: " + clientes.idClienteDTO + "\ntoken usuario: " + tokenUsuario);
                    console.log("nome cliente: " + clientes.nomeClienteDTO + "\nnome usuario: " + nomeUsuario);
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
                            {integrante.nomeClienteDTO && integrante.nomeClienteDTO === nomeUsuario ? (
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