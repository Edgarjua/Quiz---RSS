const quizQuestions = [
    {
        question: "Qual é o objetivo principal do gerenciamento de resíduos de serviços de saúde?",
        options: ["Reduzir os custos de coleta", "Definir medidas de segurança para proteger o trabalhador e o meio ambiente"],
        correctAnswer: 1
    },
    {
        question: "Qual é o primeiro passo no manejo dos resíduos de serviços de saúde?",
        options: ["Segregação na fonte", "Transporte para o aterro sanitário"],
        correctAnswer: 0
    },
    {
        question: "De acordo com a classificação de resíduos, o Grupo A abrange resíduos com risco de:",
        options: ["Infecção", "Radiação"],
        correctAnswer: 0
    },
    {
        question: "Qual grupo de resíduos inclui materiais perfurocortantes?",
        options: ["Grupo C", "Grupo E"],
        correctAnswer: 1
    },
    {
        question: "A legislação mais relevante para o gerenciamento de resíduos de saúde no Brasil é:",
        options: ["RDC ANVISA nº 306/2004", "RDC ANVISA nº 60/2002"],
        correctAnswer: 0
    },
    {
        question: "Os resíduos químicos fazem parte de qual grupo?",
        options: ["Grupo B", "Grupo D"],
        correctAnswer: 0
    },
    {
        question: "De acordo com o PGRSS, a segregação dos resíduos deve ocorrer:",
        options: ["No transporte","No momento e local de geração"],
        correctAnswer: 1
    },
    {
        question: "O que é recomendado quanto ao preenchimento dos sacos plásticos utilizados para acondicionamento de resíduos?",
        options: ["Devem ser preenchidos até 2/3 da capacidade", "Devem ser preenchidos até o limite máximo"],
        correctAnswer: 0
    },
    {
        question: "Qual é a cor recomendada para os sacos usados no descarte de resíduos biológicos?",
        options: ["Pretos", "Brancos"],
        correctAnswer: 1
    },
    {
        question: "Os resíduos radioativos pertencem a qual grupo?",
        options: ["Grupo C", "Grupo E"],
        correctAnswer: 0
    }
];

let currentQuestionIndex = 0;
let score = 0;
let participantName = '';

// Função para iniciar o quiz
function startQuiz() {
    participantName = document.getElementById('participantName').value.trim();
    if (participantName === '') {
        alert('Por favor, insira seu nome.');
        return;
    }
    document.getElementById('startContainer').style.display = 'none'; // Esconde o campo de nome
    document.getElementById('quizContainer').style.display = 'block'; // Exibe o quiz
    displayQuestion();
}

// Exibir a pergunta atual
function displayQuestion() {
    const quizContainer = document.getElementById('quiz');
    quizContainer.innerHTML = ''; // Limpar conteúdo anterior
    const question = quizQuestions[currentQuestionIndex];
    
    const questionDiv = document.createElement('div');
    questionDiv.className = 'question';
    
    const questionTitle = document.createElement('h3');
    questionTitle.innerText = `${currentQuestionIndex + 1}. ${question.question}`;
    questionDiv.appendChild(questionTitle);
    
    const optionsDiv = document.createElement('div');
    optionsDiv.className = 'options';
    
    question.options.forEach((option, i) => {
        const optionLabel = document.createElement('label');
        const optionInput = document.createElement('input');
        optionInput.type = 'radio';
        optionInput.name = 'option';
        optionInput.value = i;
        optionInput.onclick = () => enableNextButton(i); // Ativa o botão "Próxima" ao selecionar uma opção
        
        optionLabel.appendChild(optionInput);
        optionLabel.appendChild(document.createTextNode(option));
        optionsDiv.appendChild(optionLabel);
    });
    
    questionDiv.appendChild(optionsDiv);
    quizContainer.appendChild(questionDiv);
    document.getElementById('nextBtn').disabled = true; // Desativa o botão "Próxima"
}

// Habilita o botão "Próxima"
function enableNextButton(selectedOption) {
    document.getElementById('nextBtn').disabled = false;
    quizQuestions[currentQuestionIndex].selectedOption = selectedOption; // Armazena a resposta selecionada
}

// Avança para a próxima pergunta
function nextQuestion() {
    const selectedOption = quizQuestions[currentQuestionIndex].selectedOption;
    if (selectedOption === quizQuestions[currentQuestionIndex].correctAnswer) {
        score++;
    }
    
    currentQuestionIndex++;
    
    if (currentQuestionIndex < quizQuestions.length) {
        displayQuestion(); // Exibe a próxima pergunta
    } else {
        displayResult(); // Exibe o resultado final
    }
}

// Exibe o resultado final
function displayResult() {
    const quizContainer = document.getElementById('quiz');
    quizContainer.innerHTML = `<h2>${participantName}, você acertou ${score} de ${quizQuestions.length} perguntas.</h2>`;
    document.getElementById('nextBtn').style.display = 'none'; // Esconde o botão "Próxima"
}

// Inicializar o quiz ao carregar a página
window.onload = function() {
    document.getElementById('quizContainer').style.display = 'none'; // Esconder o quiz inicialmente
};
