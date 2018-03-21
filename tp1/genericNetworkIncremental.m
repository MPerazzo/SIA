X1_POS = 1;
X2_POS = 2;
Y_POS = 3;

input_file = readParam('input_file');
file_data = importdata(input_file);

x1 = file_data.data(:, X1_POS);
x2 = file_data.data(:, X2_POS);
y = file_data.data(:, Y_POS);

neurons = readParam('neurons');
neurons_size = size(neurons);
layers = neurons_size(2);
epochs = readParam('epochs');
eta = readParam('eta');
epsilon = readParam('epsilon');
pause_gamma = 0.00000001;
        
terrainSize = size(y, 1);
trainingSize = readParam('training_size');
testingSize = terrainSize - trainingSize;

%normalization of input
%{
x1 = x1 / norm(x1);

x2 = x2 / norm(x2);

y = y / norm(y);
%}

%training and testing domains
training_input_domain = [-1*ones(trainingSize, 1) x1(1:trainingSize) x2(1:trainingSize)]';
testing_input_domain = [-1*ones(testingSize, 1) x1((trainingSize+1):terrainSize) x2((trainingSize+1):terrainSize)]';
expected_output = y';

%generic cells
weights_cell = cell(layers - 1, 1);
weighted_sum_cell = cell(layers - 1, 1);
ones_cell = cell(layers - 1, 1);
training_delta_cell = cell(layers - 1, 1);
testing_weighted_sum_cell = cell(layers - 1, 1);

testing_error = 0;
training_error = 0;

for k = 1:(layers-1)
    weights_cell{k} = rand(neurons(k+1), neurons(k)+1);
    training_delta_cell{k} = zeros(neurons(k+1), 1);
    ones_cell{k} = ones(neurons(k+1), 1);
    
    if (k ~= layers-1)
        weighted_sum_cell{k} = [-1*ones(trainingSize,1) zeros(trainingSize, neurons(k+1))]';
        testing_weighted_sum_cell{k} = [-1*ones(testingSize,1) zeros(testingSize, neurons(k+1))]';
    else
        weighted_sum_cell{k} = zeros(neurons(k+1), trainingSize);
        testing_weighted_sum_cell{k} = zeros(neurons(k+1), testingSize);
    end
end

if readParam('tanh')
    activation_function = @tanh;
    activation_function_derivate = @(x, k)(ones_cell{k} - x.^2);
else
    activation_function = @(x)(exp(-x));
    activation_function_derivate = @(x, k)(x'*(ones_cell{k}-x));
end

hold on
ylim([0 0.001])
xlabel('epochs')
ylabel('errors')
%ylabel('porcentage of success')

for i = 1:epochs
    for j = 1:trainingSize
        %forward
        forward_previous = training_input_domain;
        for k = 1:(layers - 1)
            if k == layers - 1
                weighted_sum_cell{k}(j) = tanh(weights_cell{k} * forward_previous(:, j));
            else
                weighted_sum_cell{k}(2:neurons(k+1) + 1, j) = activation_function(weights_cell{k} * forward_previous(:, j));
            end
            forward_previous = weighted_sum_cell{k};
        end
        %back propagation
        for k = (layers - 1):-1:1
            if k == layers - 1
                training_delta_cell{k} = (ones_cell{k} - weighted_sum_cell{k}(j).^2).*(expected_output(j) - weighted_sum_cell{k}(j));
            else
                training_delta_cell{k} = activation_function_derivate(weighted_sum_cell{k}(2:neurons(k+1) + 1, j), k).*(weights_cell{k+1}(:, 2:neurons(k+1) + 1)' * training_delta_cell{k+1});
            end
           
            if k == 1
                backward_previous = training_input_domain(:, j)';
            else
                backward_previous = weighted_sum_cell{k-1}(:,j)';
            end
            
            weights_cell{k} = weights_cell{k} + eta * training_delta_cell{k} * backward_previous;
        end
    end
            
    %training error
    training_error_prev = training_error;
    training_error = 0.5*sum((expected_output(1:trainingSize) - weighted_sum_cell{layers-1}).^2)/trainingSize;
    %training_error = abs((expected_output(1:trainingSize) - weighted_sum_cell{layers-1}));
    
    %{
    counter = 0;
    for k = 1:trainingSize
        if (training_error(k) < epsilon)
            counter = counter + 1;
        end
    end
    training_success_rate = (counter/trainingSize) * 100.0;
    %}
    
    %testing error
    testing_forward_previous = testing_input_domain;    
    for k = 1:(layers - 1)
        if k == layers - 1
            testing_weighted_sum_cell{k} = tanh(weights_cell{k} * testing_forward_previous);
        else
            testing_weighted_sum_cell{k}(2:neurons(k+1) + 1, :) = activation_function(weights_cell{k} * testing_forward_previous);
        end
        testing_forward_previous = testing_weighted_sum_cell{k};
    end
        
    testing_error_prev = testing_error;
    testing_error = 0.5*sum((expected_output((trainingSize+1):terrainSize) - testing_weighted_sum_cell{layers-1}).^2)/(testingSize);
    %testing_error = abs((expected_output((trainingSize+1):terrainSize) - testing_weighted_sum_cell{layers-1}));
    
    %{
    counter = 0;
    for k = 1:testingSize
        if (testing_error(k) < epsilon)
            counter = counter + 1;
        end
    end
    testing_success_rate = (counter/testingSize) * 100.0;
    %}
    
    %shuffling input and output
    %aux = [training_input_domain;expected_output(1:trainingSize)];
    %aux = aux(:, randperm(trainingSize));
    %training_input_domain = aux(1:3, :);
    %expected_output = [aux(4, :) expected_output(trainingSize + 1:terrainSize)];
    
    training_error
    testing_error
    
    if (i == 1)
        training_error_prev = training_error;
        testing_error_prev = testing_error;
    end
    
    plot((i-1):i, [ training_error_prev training_error ], 'r')
    plot((i-1):i, [ testing_error_prev testing_error ],'b')
    %plot(i, training_success_rate, '.r')
    %plot(i, testing_success_rate,'.b')
    pause(pause_gamma)
    %legend('Error de aprendizaje','Error de testeo')

end

hold off

figure
scatter3(x1, x2, y,'RED','filled')
hold on
scatter3(x1, x2, [weighted_sum_cell{layers-1} testing_weighted_sum_cell{layers-1}],'BLUE','filled')
