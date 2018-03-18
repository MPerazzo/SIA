X1_POS = 1;
X2_POS = 2;
Y_POS = 3;

input_file = 'input.txt';

layers = 4;
neurons = [2 5 5 1];
epochs = 3000;
eta = 0.005;
pause_gamma = 0.00000001;

file_data = importdata(input_file);

terrainSize = size(file_data.data(:,1), 1);
trainingSize = 300;
testingSize = terrainSize - trainingSize;

training_input_domain = [-1*ones(trainingSize, 1) file_data.data(1:trainingSize, X1_POS) file_data.data(1:trainingSize, X2_POS)]';
testing_input_domain = [-1*ones(testingSize, 1) file_data.data((trainingSize+1):terrainSize, X1_POS) file_data.data((trainingSize+1):terrainSize, X2_POS)]';
expected_output = file_data.data(:, Y_POS)';


weights_cell = cell(layers - 1);
weighted_sum_cell = cell(layers - 1);
ones_cell = cell(layers - 2);
learning_delta_cell = cell(layers - 1);
testing_weighted_sum_cell = cell(layers - 1);


for k = 1:(layers-1)
    weights_cell{k} = rand(neurons(k+1), neurons(k)+1);
    learning_delta_cell{k} = zeros(neurons(k+1), 1);
    
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
ylim([0 0.001])
xlabel('Epochs')
ylabel('Errors')

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
                learning_delta_cell{k} = (expected_output(j) - weighted_sum_cell{k}(j));
            else
                learning_delta_cell{k} = (ones_cell{k} - weighted_sum_cell{k}(2:neurons(k+1) + 1, j).^2).*(weights_cell{k+1}(:, 2:neurons(k+1) + 1)' * learning_delta_cell{k+1});
            end
           
            if k == 1
                backward_previous = training_input_domain(:, j)';
            else
                backward_previous = weighted_sum_cell{k-1}(:,j)';
            end
            
            weights_cell{k} = weights_cell{k} + eta * learning_delta_cell{k} * backward_previous;
        end
    end
    %shuffling training input
    training_input_domain(:,randperm(trainingSize));
            
    %learning error
    learning_error = 0.5*sum((expected_output(1:trainingSize) - weighted_sum_cell{layers-1}).^2)/trainingSize;

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
        
    testing_error = 0.5*sum((expected_output((trainingSize+1):terrainSize) - testing_weighted_sum_cell{layers-1}).^2)/(testingSize);

    plot(i, learning_error,'.r')
    plot(i, testing_error,'.b')
    
    pause(pause_gamma)
    %legend('Error de aprendizaje','Error de testeo')

end

hold off
learning_error
testing_error

%figure
%scatter3(A.data(:, 1), A.data(:, 2), A.data(:, 3),'RED','filled')
%hold on
%scatter3(A.data(:, 1), A.data(:, 2), [V3 V3T],'BLUE','filled')
