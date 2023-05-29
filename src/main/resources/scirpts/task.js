document.getElementById('create-task-link')
        .addEventListener('click', function(e) {
            e.preventDefault();
            document.getElementById('create-task-form').style.display = 'block';
        });