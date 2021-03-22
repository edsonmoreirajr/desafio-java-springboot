<div><p><img alt="Logo Compasso" src="https://compasso.com.br/wp-content/uploads/2020/07/LogoCompasso-Negativo.png"></p>
<h1 id="markdown-header-catalogo-de-produtos">Catálogo de produtos</h1>
<p>Sua tarefa é implementar um catálogo de produtos com Java e Spring Boot.</p>
<h2 id="markdown-header-product-ms">product-ms</h2>
<p>Neste microserviço deve ser possível criar, alterar, visualizar e excluir um determinado produto, além de visualizar a lista de produtos atuais disponíveis. Também deve ser possível realizar a busca de produtos filtrando por <em>name, description e price</em>.</p>
<h3 id="markdown-header-formato">Formato</h3>
<p>O formato esperado de um produto é o seguinte:</p>
<div class="codehilite language-javascript"><pre><span></span>  <span class="p">{</span>
    <span class="s2">"id"</span><span class="o">:</span> <span class="s2">"string"</span><span class="p">,</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"string"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"string"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="mf">59.99</span>
  <span class="p">}</span>
</pre></div>


<p>Durante a criação e alteração, os campos <em>name, description e price</em> são obrigatórios. Em relação ao campo <em>price</em> o valor deve ser positivo.</p>
<h3 id="markdown-header-endpoints">Endpoints</h3>
<p>Devem ser disponibilizados os seguintes endpoints para operação do catálogo de produtos:</p>
<table>
<thead>
<tr>
<th>Verbo HTTP</th>
<th align="center">Resource path</th>
<th align="right">Descrição</th>
</tr>
</thead>
<tbody>
<tr>
<td>POST</td>
<td align="center">/products</td>
<td align="right">Criação de um produto</td>
</tr>
<tr>
<td>PUT</td>
<td align="center">/products/{id}</td>
<td align="right">Atualização de um produto</td>
</tr>
<tr>
<td>GET</td>
<td align="center">/products/{id}</td>
<td align="right">Busca de um produto por ID</td>
</tr>
<tr>
<td>GET</td>
<td align="center">/products</td>
<td align="right">Lista de produtos</td>
</tr>
<tr>
<td>GET</td>
<td align="center">/products/search</td>
<td align="right">Lista de produtos filtrados</td>
</tr>
<tr>
<td>DELETE</td>
<td align="center">/products/{id}</td>
<td align="right">Deleção de um produto</td>
</tr>
</tbody>
</table>
<h4 id="markdown-header-post-products">POST /products</h4>
<p>Esse endpoint deve criar um novo produto na base de dados, ao receber o JSON do produto o mesmo deverá ser validado em acordo com as regras da seção <strong>Formato</strong>, e, caso esteja válido, persistido na base de dados e retornado com o <em>id</em> gerado e HTTP 201.</p>
<p>Entrada:</p>
<div class="codehilite language-javascript"><pre><span></span>  <span class="p">{</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">}</span>
</pre></div>


<p>Retorno:</p>
<div class="codehilite language-javascript"><pre><span></span>  <span class="p">{</span>
    <span class="s2">"id"</span><span class="o">:</span> <span class="s2">"id gerado"</span><span class="p">,</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">}</span>
</pre></div>


<p>Em caso de algum erro de validação, a API deve retornar um HTTP 400 indicando uma requisição inválida. O JSON retornado nesse caso deve seguir o seguinte formato:</p>
<div class="codehilite language-javascript"><pre><span></span>  <span class="p">{</span>
    <span class="s2">"status_code"</span><span class="o">:</span> <span class="nx">integer</span><span class="p">,</span>
    <span class="s2">"message"</span><span class="o">:</span> <span class="s2">"string"</span>
  <span class="p">}</span>
</pre></div>


<p>No campo <em>status_code</em> deve vir o código HTTP do erro de validação (400 Bad Request). No campo <em>message</em> deverá vir uma mensagem genérica indicando o erro ocorrido.</p>
<h4 id="markdown-header-put-products123id125">PUT /products/{id}</h4>
<p>Esse endpoint deve atualizar um produto baseado no {id} passado via path param. Antes de alterar, deve ser consultada a base de dados pelo <em>id</em>, se o produto NÃO for localizado então devolver um HTTP 404 ao cliente. Se localizar o produto, então os campos <em>name, description e price</em> devem ser atualizados conforme recebidos no body da requisição.</p>
<p>Entrada:</p>
<div class="codehilite language-javascript"><pre><span></span>  <span class="p">{</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">}</span>
</pre></div>


<p>Retorno:</p>
<div class="codehilite language-javascript"><pre><span></span>  <span class="p">{</span>
    <span class="s2">"id"</span><span class="o">:</span> <span class="s2">"id atualizado"</span><span class="p">,</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">}</span>
</pre></div>


<p>Importante que antes da atualização as mesmas regras de validação do POST /products devem ser executadas para garantir consistência, e, se ocorrer erro retornar no mesmo formato:</p>
<div class="codehilite language-javascript"><pre><span></span>  <span class="p">{</span>
    <span class="s2">"status_code"</span><span class="o">:</span> <span class="nx">integer</span><span class="p">,</span>
    <span class="s2">"message"</span><span class="o">:</span> <span class="s2">"string"</span>
  <span class="p">}</span>
</pre></div>


<h4 id="markdown-header-get-products123id125">GET /products/{id}</h4>
<p>Esse endpoint deve retornar o product localizado na base de dados com um HTTP 200. Em caso de não localização do produto, a API deve retornar um HTTP 404 indicando que o recurso não foi localizado, não há necessidade de retornar um JSON (response body) nesse caso.</p>
<p>Retorno:</p>
<div class="codehilite language-javascript"><pre><span></span>  <span class="p">{</span>
    <span class="s2">"id"</span><span class="o">:</span> <span class="s2">"id buscado"</span><span class="p">,</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">}</span>
</pre></div>


<h4 id="markdown-header-get-products">GET /products</h4>
<p>Nesse endpoint a API deve retornar a lista atual de todos os produtos com HTTP 200. Se não existir produtos, retornar uma lista vazia.</p>
<p>Retorno com produtos:</p>
<div class="codehilite language-javascript"><pre><span></span><span class="p">[</span>
  <span class="p">{</span>
    <span class="s2">"id"</span><span class="o">:</span> <span class="s2">"id produto 1"</span><span class="p">,</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">},</span>
  <span class="p">{</span>
    <span class="s2">"id"</span><span class="o">:</span> <span class="s2">"id produto 2"</span><span class="p">,</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">}</span>
<span class="p">]</span>
</pre></div>


<p>Retorno vazio:</p>
<div class="codehilite language-javascript"><pre><span></span><span class="p">[]</span>
</pre></div>


<h4 id="markdown-header-get-productssearch">GET /products/search</h4>
<p>Nesse endpoint a API deve retornar a lista atual de todos os produtos filtrados de acordo com query parameters passados na URL.</p>
<p>Os query parameters aceitos serão: q, min_price e max_price.</p>
<p><strong>Importante: nenhum deles deverá ser obrigatório na requisição</strong></p>
<p>Onde:</p>
<table>
<thead>
<tr>
<th>Query param</th>
<th align="center">Ação de filtro</th>
</tr>
</thead>
<tbody>
<tr>
<td>q</td>
<td align="center">deverá bater o valor contra os campos <em>name</em> e <em>description</em></td>
</tr>
<tr>
<td>min_price</td>
<td align="center">deverá bater o valor "&gt;=" contra o campo <em>price</em></td>
</tr>
<tr>
<td>max_price</td>
<td align="center">deverá bater o valor "&lt;=" contra o campo <em>price</em></td>
</tr>
</tbody>
</table>
<p><strong>Exemplo: /products/search?min_price=10.5&amp;max_price=50&amp;q=superget</strong></p>
<p>Retorno com produtos filtrados/buscados:</p>
<div class="codehilite language-javascript"><pre><span></span><span class="p">[</span>
  <span class="p">{</span>
    <span class="s2">"id"</span><span class="o">:</span> <span class="s2">"id produto 1"</span><span class="p">,</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">},</span>
  <span class="p">{</span>
    <span class="s2">"id"</span><span class="o">:</span> <span class="s2">"id produto 2"</span><span class="p">,</span>
    <span class="s2">"name"</span><span class="o">:</span> <span class="s2">"nome"</span><span class="p">,</span>
    <span class="s2">"description"</span><span class="o">:</span> <span class="s2">"descrição"</span><span class="p">,</span>
    <span class="s2">"price"</span><span class="o">:</span> <span class="o">&lt;</span><span class="nx">preco</span><span class="o">&gt;</span>
  <span class="p">}</span>
<span class="p">]</span>
</pre></div>


<p>Retorno vazio:</p>
<div class="codehilite language-javascript"><pre><span></span><span class="p">[]</span>
</pre></div>


<h4 id="markdown-header-delete-products123id125">DELETE /products/{id}</h4>
<p>Esse endpoint deve deletar um registro de produto na base de dados. Caso encontre o produto filtrando pelo <em>id</em> então deve deletar e retornar um HTTP 200. Se o <em>id</em> passado não foi localizado deve retornar um HTTP 404</p>
<h2 id="markdown-header-validacao">Validação</h2>
<p>A validação dos endpoints e sua correta funcionalidade será através de script automatizado. Logo, é importante que você defina a porta do serviço como sendo 9999, ficando a base url então: http://localhost:9999</p>
<p>Também ocorrerá avaliação técnica do código-fonte produzido, bem como eventual análise estática do mesmo.</p>
<h2 id="markdown-header-entrega-do-codigo">Entrega do código</h2>
<p>Você é responsável por entregar o código da forma como achar mais adequado, bem como eventuais documentações necessárias para a execução do seu microserviço.</p>
<h3 id="markdown-header-bom-desafio-m">Bom desafio \m/</h3></div>
