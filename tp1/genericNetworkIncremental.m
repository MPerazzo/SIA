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
epsilon = readParam('epsilon_error');
error_treshold_value = readParam('error_treshold_value');
error_treshold_flag = readParam('error_treshold_flag');
shuffle_flag = readParam('shuffle_flag');
adaptative_eta_flag = readParam('adaptative_eta_flag');
eta_check_steps = readParam('eta_check_steps');
eta_increase_value = readParam('eta_increase_value');
eta_decrease_factor = readParam('eta_decrease_factor');
momentum_flag = readParam('momentum_flag');
alpha_momentum_init = readParam('alpha_momentum');
alpha_momentum = alpha_momentum_init;
pause_gamma = 0.00000001;


if readParam('tanh')
    activation_function = @tanh;
    activation_function_derivate = @(x)(1 - x.^2);
else
    activation_function = @(x)(1./(1 + exp(-x)));
    activation_function_derivate = @(x)(x.*(1-x));
end

if readParam('save_seed_flag') || readParam('load_seed_flag')
    current_seed = rng;
    current_profile = getenv('USERPROFILE');
    splitted = strsplit(current_profile, '\');
    splitted_size = size(splitted);
    profile_name = splitted{splitted_size(2)};
end

if readParam('load_seed_flag')
    path = strcat('\\opc-w-fs04\ctxusers$\', profile_name, '\Desktop\', readParam('seed_id'));
    load(path, 'seed_state');
    current_seed.State = seed_state;
    rng(current_seed);
end
        
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
previous_weights_variation_cell = cell(layers - 1, 1);
weighted_sum_cell = cell(layers - 1, 1);
training_delta_cell = cell(layers - 1, 1);
testing_weighted_sum_cell = cell(layers - 1, 1);

testing_cuadratic_error = 0;
training_cuadratic_error = 0;
training_cuadratic_old_error = 0;

%generic cells initialization
for k = 1:(layers-1)
    weights_cell{k} = rand(neurons(k+1), neurons(k)+1);
    previous_weights_variation_cell{k} = zeros(neurons(k+1), neurons(k)+1);
    training_delta_cell{k} = zeros(neurons(k+1), 1);
    
    if (k ~= layers-1)
        weighted_sum_cell{k} = [-1*ones(trainingSize,1) zeros(trainingSize, neurons(k+1))]';
        testing_weighted_sum_cell{k} = [-1*ones(testingSize,1) zeros(testingSize, neurons(k+1))]';
    else
        weighted_sum_cell{k} = zeros(neurons(k+1), trainingSize);
        testing_weighted_sum_cell{k} = zeros(neurons(k+1), testingSize);
    end
end

weights_old_cell = weights_cell; 

figure('units', 'normalized', 'outerposition', [0 0 1 1])
hold on
xlabel('epochs')
ylabel('errors')
plot(0, 0, '.r')
plot(0, 0, '.b')
[h, icons, plots, legend_text] = legend('Error de aprendizaje', 'Error de testeo');
%legend marker sizes, font size, and box size
h.Position(4) = h.Position(4) + 0.01;
h.Position(3) = h.Position(3) + 0.09;
h.Position(1) = h.Position(1) - 0.1;
icons(1).FontSize = 16;
icons(2).FontSize = 16;
icons(4).MarkerSize = 24;
icons(6).MarkerSize = 24;

for i = 1:epochs
    for j = 1:trainingSize
        %shuffling
        r = shuffle_flag * randi([1 trainingSize],1,1) + (1 - shuffle_flag) * j;
        %r=j;
        %forward
        forward_previous = training_input_domain;
        for k = 1:(layers - 1)
            if k == layers - 1
                weighted_sum_cell{k}(r) = tanh(weights_cell{k} * forward_previous(:, r));
            else
                weighted_sum_cell{k}(2:neurons(k+1) + 1, r) = activation_function(weights_cell{k} * forward_previous(:, r));
            end
            forward_previous = weighted_sum_cell{k};
        end
        %back propagation
        for k = (layers - 1):-1:1
            if k == layers - 1
                training_delta_cell{k} = (1 - weighted_sum_cell{k}(r).^2).*(expected_output(r) - weighted_sum_cell{k}(r));
            else
                training_delta_cell{k} = activation_function_derivate(weighted_sum_cell{k}(2:neurons(k+1) + 1, r)).*(weights_cell{k+1}(:, 2:neurons(k+1) + 1)' * training_delta_cell{k+1});
            end
           
            if k == 1
                backward_previous = training_input_domain(:, r)';
            else
                backward_previous = weighted_sum_cell{k-1}(:,r)';
            end
            weight_variation =  eta * training_delta_cell{k} * backward_previous;
            weights_cell{k} = weights_cell{k} + weight_variation + momentum_flag * alpha_momentum * previous_weights_variation_cell{k};
            previous_weight_variation_cell{k} = weight_variation;
        end
    end
    
    %training error
    training_cuadratic_error_prev = training_cuadratic_error;
    training_cuadratic_error = 0.5*sum((expected_output(1:trainingSize) - weighted_sum_cell{layers-1}).^2)/trainingSize;
    training_abs_error = abs((expected_output(1:trainingSize) - weighted_sum_cell{layers-1}));
    
    
    counter = 0;
    for k = 1:trainingSize
        if (training_abs_error(k) < epsilon)
            counter = counter + 1;
        end
    end
    training_success_rate = (counter/trainingSize) * 100.0;
    
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
        
    testing_cuadratic_error_prev = testing_cuadratic_error;
    testing_cuadratic_error = 0.5*sum((expected_output((trainingSize+1):terrainSize) - testing_weighted_sum_cell{layers-1}).^2)/(testingSize);
    testing_abs_error = abs((expected_output((trainingSize+1):terrainSize) - testing_weighted_sum_cell{layers-1}));
    
    counter = 0;
    for k = 1:testingSize
        if (testing_abs_error(k) < epsilon)
            counter = counter + 1;
        end
    end
    testing_success_rate = (counter/testingSize) * 100.0;
       
    if i==1
        training_cuadratic_old_error = training_cuadratic_error; 
    end
    
    %adaptative eta
    if (rem(i, eta_check_steps) == 0 && adaptative_eta_flag)
       if(training_cuadratic_error > training_cuadratic_old_error)
           weights_cell = weights_old_cell;
           eta = eta*eta_decrease_factor;
           alpha_momentum = 0;
        elseif(training_cuadratic_error < training_cuadratic_old_error)
           eta = eta + eta_increase_value;
           weights_old_cell = weights_cell;
           alpha_momentum = alpha_momentum_init;
        end
        training_cuadratic_old_error = training_cuadratic_error;
    end
     
    training_cuadratic_error
    testing_cuadratic_error
    training_success_rate
    testing_success_rate
    
    if (i == 1)
        training_cuadratic_error_prev = training_cuadratic_error;
        testing_cuadratic_error_prev = testing_cuadratic_error;
    end
    
    plot((i-1):i, [ training_cuadratic_error_prev training_cuadratic_error ], 'r')
    plot((i-1):i, [ testing_cuadratic_error_prev testing_cuadratic_error ],'b')
    %plot(i, training_success_rate, '.r')
    %plot(i, testing_success_rate,'.b')
    pause(pause_gamma)
    %legend('Error de aprendizaje','Error de testeo')
    
    if (error_treshold_flag)
        if (training_cuadratic_error <= error_treshold_value)
            break
        end
    end

end

hold off

if (readParam('save_seed_flag'))    
    neurons_to_string = '';
    for i = 1:layers
        neurons_to_string = strcat(neurons_to_string, num2str(neurons(i)));
        if (i ~= layers)
            neurons_to_string = strcat(neurons_to_string, '-');
        end
    end
    seed_state = current_seed.State;
    path = strcat('\\opc-w-fs04\ctxusers$\', profile_name, '\Desktop\seed_', neurons_to_string, '_', datestr(datetime,'mmmm-dd-yyyy-HH-MM-SS'));
    save(path,'seed_state');
end

figure
scatter3(x1, x2, y,'RED','filled')
hold on
scatter3(x1, x2, [weighted_sum_cell{layers-1} testing_weighted_sum_cell{layers-1}],'BLUE','filled')
