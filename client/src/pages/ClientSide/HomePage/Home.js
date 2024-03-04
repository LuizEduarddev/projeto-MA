import React, {useEffect, useState} from "react";
import { CiShoppingCart as Carrinho} from "react-icons/ci";
import { MdTableBar as Table} from "react-icons/md";
import { IoMdRestaurant as PedidoIcon} from "react-icons/io";
import {useNavigate, Link} from 'react-router-dom';
import api from "../../../services/api";

export default function Home()
{
    const [produtos, setProdutos] = useState([]);
    const [carrinho, setCarrinho] = useState([]);
    const [isLogado, setIsLogado] = useState(false);
    const [username, setUsername] = useState('');
    const mesaToken = localStorage.getItem('mesaToken');
    const URLmesa = '/mesa/' + localStorage.getItem('mesaToken')
    const navigate = useNavigate();

    const [quantidade, setQuantidade] = useState(1);
    const increcrementQuantidade = () => setQuantidade(quantidade + 1);
    let decrementQuantidade = () => setQuantidade(quantidade - 1);
    if (quantidade <=1){
        decrementQuantidade = () => setQuantidade(1);
    }

    useEffect(() => {
        async function getNomeCliente()
        {
            const sessionId = localStorage.getItem('clienteToken');
            if (sessionId)
            {
                api.post('http://localhost:8080/api/cliente/get-by-id/' + sessionId)
                .then(responseCliente => {
                    setIsLogado(true);
                    setUsername(responseCliente.data.nomeCliente);
                })
                .catch(error => {
                    const data = error.response.data.message;
                    if (data)
                    {
                        navigate('/login')
                    }
                    else{
                        alert('Erro de requisicao.');
                        navigate('/login') 
                    }
                })
            }
            else{
                setIsLogado(false);
            }
        }

        getNomeCliente();
    }, [])

    useEffect(() => {
        async function getProducts()
        {
            api.get('http://localhost:8080/api/produto/get-all')
            .then(response => {
            setProdutos(response.data);
            })
            .catch(error => {
            alert(error);
            });
        }
        getProducts();
    }, [produtos]);

    function addToCart(produto, quantidade) {

        let existCart = localStorage.getItem('cart')
        try{
            let cartItens = JSON.parse(existCart);
            let produtoExistente = cartItens.find(item => item.idProduto === produto.idProduto);
        
            if (produtoExistente) {
                if (produtoExistente.quantidade !== quantidade) {
                    produtoExistente.quantidade = quantidade;
                    produtoExistente.valorTotalIten = produto.precoProduto * quantidade;
                    localStorage.setItem('cart', JSON.stringify(cartItens));
                    setCarrinho([...cartItens]);
                }
            } else {
                cartItens.push({
                    nomeProduto: produto.nomeProduto,
                    idProduto: produto.idProduto,
                    quantidade: quantidade,
                    valorUnitarioItem: produto.precoProduto,
                    valorTotalIten: produto.precoProduto * quantidade
                });
                localStorage.setItem('cart', JSON.stringify(cartItens));
                setCarrinho([...cartItens]);
            }
        }
        catch(error)
        {
            const cartItens = [{
                nomeProduto: produto.nomeProduto,
                idProduto: produto.idProduto,
                quantidade: quantidade,
                valorUnitarioItem: produto.precoProduto,
                valorTotalIten: produto.precoProduto * quantidade
            }];
            localStorage.setItem('cart', JSON.stringify(cartItens));
            setCarrinho([...cartItens]);
        }
    }

    return(
        <div>
            <div>
                {isLogado ? 
                (
                    <h1>
                        Bem-vindo(a), <Link to="/cliente/perfil">{username}</Link>
                    </h1>
                ) 
                : 
                (
                    <h1>Bem-vindo(a)</h1>
                )}
            </div>
            <div>
                <button>
                    <Link to = '/cliente/carrinho'>
                        <h3>
                            <Carrinho/>
                            | Ir para o carrinho
                        </h3>
                    </Link>
                </button>
            </div>
            <div>
            <button>
                    <Link to = '/cliente/pedidos'>
                        <h3>
                            <PedidoIcon/>
                            | Ir para pedidos
                        </h3>
                    </Link>
                </button>
            </div>
            <div>
                {mesaToken ? (
                        <button>
                            <h3>
                                <Link to = {URLmesa}>
                                    <Table/>
                                    | Ver a mesa
                                </Link>
                            </h3>
                        </button>
                        ) : (
                            <div>
                                
                            </div>
                        )
                        }
            </div>

            <div>
                <h1><strong>PROMOÇOES DO DIA!</strong></h1>
                <ul>
                    {produtos.map(promocao =>
                        promocao.promoProduto === 1 && (
                            <li key={promocao.idProduto}>
                                <p><strong>{promocao.nomeProduto}</strong></p>
                                <p>{promocao.descricaoProduto}</p>
                                <p>{promocao.precoProduto}</p>
                            </li>
                        )
                    )}
                </ul>
            </div>

            <div>
                <h1>Nossos produtos!</h1>
                <h2>Salgados</h2>
                <div>
                    <ul>
                        {produtos.map(produto =>
                            produto.classeProduto === 'salgado' && (
                                <li key={produto.idProduto}>
                                    <p><strong>{produto.nomeProduto}</strong></p>
                                    <p>{produto.descricaoProduto}</p>
                                    <p>R$ {produto.precoProduto}</p>
                                    <button onClick={() => addToCart(produto, quantidade)}>
                                        <Carrinho/>|
                                        Adicionar ao carrinho
                                    </button>
                                    <div>
                                        <button onClick={decrementQuantidade}>
                                            -
                                        </button>
                                        <p>{quantidade}</p>
                                        <button onClick={increcrementQuantidade }>
                                            +
                                        </button>
                                    </div>
                                </li>
                            )
                        )}
                    </ul>
                </div>
                
                <h2>Sobremesas</h2>
                <div>
                    <ul>
                        {produtos.map(produto =>
                            produto.classeProduto === 'sobremesa' && (
                                <li key={produto.idProduto}>
                                    <p><strong>{produto.nomeProduto}</strong></p>
                                    <p>{produto.descricaoProduto}</p>
                                    <p>R$ {produto.precoProduto}</p>
                                    <button onClick={() => addToCart(produto, quantidade)}>
                                        <Carrinho/>|
                                        Adicionar ao carrinho
                                    </button>
                                    <div>
                                        <button onClick={decrementQuantidade}>
                                            -
                                        </button>
                                        <p>{quantidade}</p>
                                        <button onClick={increcrementQuantidade }>
                                            +
                                        </button>
                                    </div>
                                </li>
                            )
                        )}
                    </ul>
                </div>

                <h2>Docinhos</h2>
                <div>
                    <ul>
                        {produtos.map(produto =>
                            produto.classeProduto === 'docinhos' && (
                                <li key={produto.idProduto}>
                                    <p><strong>{produto.nomeProduto}</strong></p>
                                    <p>{produto.descricaoProduto}</p>
                                    <p>R$ {produto.precoProduto}</p>
                                    <button onClick={() => addToCart(produto, quantidade)}>
                                        <Carrinho/>|
                                        Adicionar ao carrinho
                                    </button>
                                    <div>
                                        <button onClick={decrementQuantidade}>
                                            -
                                        </button>
                                        <p>{quantidade}</p>
                                        <button onClick={increcrementQuantidade }>
                                            +
                                        </button>
                                    </div>
                                </li>
                            )
                        )}
                    </ul>
                </div>

                <h2>Tortas</h2>
                <div>
                    <ul>
                        {produtos.map(produto =>
                            produto.classeProduto === 'torta' && (
                                <li key={produto.idProduto}>
                                    <p><strong>{produto.nomeProduto}</strong></p>
                                    <p>{produto.descricaoProduto}</p>
                                    <p>R$ {produto.precoProduto}</p>
                                    <button onClick={() => addToCart(produto, quantidade)}>
                                        <Carrinho/>|
                                        Adicionar ao carrinho
                                    </button>
                                    <div>
                                        <button onClick={decrementQuantidade}>
                                            -
                                        </button>
                                        <p>{quantidade}</p>
                                        <button onClick={increcrementQuantidade }>
                                            +
                                        </button>
                                    </div>
                                </li>
                            )
                        )}
                    </ul>
                </div>
            </div>
        </div>
    );
}