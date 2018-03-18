X1_POS = 1;
X2_POS = 2;
Y_POS = 3;

input_file = 'input.txt';

layers = 4;
neurons = [2 5 5 1];
epochs = 3000;
eta = 0.005;
pause_gamma = 0.00000001;
epsilon = 0.01;

file_data = importdata(input_file);

terrainSize = size(file_data.data(:,1), 1);
trainingSize = 300;
testingSize = terrainSize - trainingSize;

training_input_domain = [-1*ones(trainingSize, 1) file_data.data(1:trainingSize, X1_POS) file_data.data(1:trainingSize, X2_POS)]';
testing_input_domain = [-1*ones(testingSize, 1) file_data.data((trainingSize+1):terrainSize, X1_POS) file_data.data((trainingSize+1):terrainSize, X2_POS)]';
expected_output = file_data.data(:, Y_POS)';

%normalization of input
training_input_domain = training_input_domain / norm(training_input_domain);
testing_input_domain = testing_input_domain / norm(testing_input_domain);
expected_output = expected_output / norm(expected_output);

weights_cell = cell(layers - 1);
weighted_sum_cell = cell(layers - 1);
ones_cell = cell(layers - 2);
training_delta_cell = cell(layers - 1);
testing_weighted_sum_cell = cell(layers - 1);


for k = 1:(layers-1)
    weights_cell{k} = rand(neurons(k+1), neurons(k)+1);
    training_delta_cell{k} = zeros(neurons(k+1), 1);
    
    if (k ~= layers-1)
        weighted_sum_cell{k} = [-1*ones(trainingSize,1) zeros(trainingSize, neurons(k+1))]';
        testing_weighted_sum_cell{k} = [-1*ones(testingSize,1) zeros(testingSize, neurons(k+1))]';
        ones_cell{k} = ones(neurons(k+1), 1);
    else
        weighted_sum_cell{k} = zeros(neurons(k+1), trainingSize);
        testing_weighted_sum_cell{k} = zeros(neurons(k+1), testingSize);
    end
end

hold on
%ylim([0 0.001])
xlabel('Epochs')
ylabel('Porcentage of success')

for i = 1:epochs
    for j = 1:trainingSize
        %forward
        forward_previous = training_input_domain;
        for k = 1:(layers - 1)
            if k == layers - 1
                weighted_sum_cell{k}(j) = weights_cell{k} * forward_previous(:, j);
            else
                weighted_sum_cell{k}(2:neurons(k+1) + 1, j) = tanh(weights_cell{k} * forward_previous(:, j));
            end
            forward_previous = weighted_sum_cell{k};
        end
        %back propagation
        for k = (layers - 1):-1:1
            if k == layers - 1
                training_delta_cell{k} = (expected_output(j) - weighted_sum_cell{k}(j));
            else
                training_delta_cell{k} = (ones_cell{k} - weighted_sum_cell{k}(2:neurons(k+1) + 1, j).^2).*(weights_cell{k+1}(:, 2:neurons(k+1) + 1)' * training_delta_cell{k+1});
            end
           
            if k == 1
                backward_previous = training_input_domain(:, j)';
            else
                backward_previous = weighted_sum_cell{k-1}(:,j)';
            end
            
            weights_cell{k} = weights_cell{k} + eta * training_delta_cell{k} * backward_previous;
        end
    end
    %shuffling training input
    training_input_domain(:,randperm(trainingSize));
            
    %training error
    %training_error = 0.5*sum((expected_output(1:trainingSize) - weighted_sum_cell{layers-1}).^2)/trainingSize;
    training_error = abs((expected_output(1:trainingSize) - weighted_sum_cell{layers-1}));
    
    counter = 0;
    for k = 1:trainingSize
        if training_error(k) < epsilon
            counter = counter + 1;
        end
    end
    training_success_rate = (counter/trainingSize) * 100.0;
    
    %testing error
    testing_forward_previous = testing_input_domain;
        for k = 1:(layers - 1)
            if k == layers - 1
                testing_weighted_sum_cell{k} = weights_cell{k} * testing_forward_previous;
            else
                testing_weighted_sum_cell{k}(2:neurons(k+1) + 1, :) = tanh(weights_cell{k} * testing_forward_previous);
            end
            testing_forward_previous = testing_weighted_sum_cell{k};
        end
        
    %testing_error = 0.5*sum((expected_output((trainingSize+1):terrainSize) - testing_weighted_sum_cell{layers-1}).^2)/(testingSize);
    testing_error = abs((expected_output((trainingSize+1):terrainSize) - testing_weighted_sum_cell{layers-1}));
    
    counter = 0;
    for k = 1:testingSize
        if testing_error(k) < epsilon
            counter = counter + 1;
        end
    end
    testing_success_rate = (counter/testingSize) * 100.0;

    plot(i, training_success_rate,'.r')
    plot(i, testing_success_rate,'.b')
    
    pause(pause_gamma)
    %legend('Error de aprendizaje','Error de testeo')

end

hold off
training_success_rate
testing_success_rate
%training_error
%testing_error

%figure
%scatter3(A.data(:, 1), A.data(:, 2), A.data(:, 3),'RED','filled')
%hold on
%scatter3(A.data(:, 1), A.data(:, 2), [V3 V3T],'BLUE','filled')
