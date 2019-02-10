package com.conformal_predictors.services;

import com.conformal_predictors.models.entity.ConfigurationResultEntity;
import com.models.entity.specialist.DatasetConfigurationEntity;
import com.models.entity.specialist.DatasetObjectsEntity;
import libsvm.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;

@Service
public class SymptomsService {
    // svm_type
    private static final String C_SVC = "C_SVC";
    private static final String NU_SVC = "NU_SVC";
    private static final String ONE_CLASS = "ONE_CLASS";
    private static final String EPSILON_SVR = "EPSILON_SVR";
    private static final String NU_SVR = "NU_SVR";

    // kernel_type
    private static final String LINEAR = "LINEAR";
    private static final String POLY = "POLY";
    private static final String RBF = "RBF";
    private static final String SIGMOID = "SIGMOID";
    private static final String PRECOMPUTED = "PRECOMPUTED";

    public ConfigurationResultEntity getConformalPrediction(ArrayList<DatasetObjectsEntity> dataSet,
                                                            DatasetObjectsEntity testObject,
                                                            DatasetConfigurationEntity datasetConfiguration) {
        ConfigurationResultEntity prediction = new ConfigurationResultEntity();

        if (testObject.getObjectClass() != null) {
            prediction.setRealClass(testObject.getObjectClass());
        }

        prediction = pPositiveCalc(dataSet, testObject, prediction, datasetConfiguration);
        prediction = pNegativeCalc(dataSet, testObject, prediction, datasetConfiguration);
        prediction = setPrediction(prediction);

        return prediction;
    }

    private ConfigurationResultEntity pPositiveCalc(ArrayList<DatasetObjectsEntity> dataSet,
                                                    DatasetObjectsEntity testObject,
                                                    ConfigurationResultEntity prediction,
                                                    DatasetConfigurationEntity datasetConfiguration) {
        testObject.setObjectClass((long) 1);
        dataSet.add(testObject);
        svm_model svmModelPositive = this.svmTrainConformalPredict(dataSet, datasetConfiguration);
        int positiveAlphaIndex = -1; // index of the checking object in model
        long alphaPositive = 0; // alpha of the checking object
        int countAlphasPositive = 0; // positive alphas in train dataSet
        ArrayList<Double> positiveSupportVectorsAlphas = new ArrayList<Double>(); // alphas of support vectors where y=1
        double pPositive = 0;
        //get positive alphas in train dataSet
        for (int k = 0; k < dataSet.size(); k++) {
            if (dataSet.get(k).getObjectClass() == 1) {
                countAlphasPositive++;
            }
        }
        //get index of the checking object in model
        //if positiveAlphaIndex == -1 - alpha of the checking object = 0
        for (int j = 0; j < svmModelPositive.sv_indices.length; j++) {
            if (svmModelPositive.sv_indices[j] == dataSet.size()) {
                positiveAlphaIndex = j;
                break;
            }
        }
        //if positiveAlphaIndex != -1 - alpha of the checking object - support vector
        //svmModelPositive.sv_coef - massive of alpha * y
        if (positiveAlphaIndex != -1) {
            alphaPositive = (long) Math.abs(svmModelPositive.sv_coef[0][positiveAlphaIndex]);
        }

        ArrayList<Double> supportVectorsAlphas = new ArrayList<Double>();
        //found alphas of support vectors where y=1
        for (int j = 0; j < svmModelPositive.sv_indices.length; j++) {
            if (svmModelPositive.sv_coef[0][j] > 0) {
                positiveSupportVectorsAlphas.add(svmModelPositive.sv_coef[0][j]);
            }
            supportVectorsAlphas.add(Math.abs(svmModelPositive.sv_coef[0][j]));
        }

        // if alpha of the checking object = 0
        if (alphaPositive == 0) {
            pPositive =
                    (double) dataSet.size()
                            /
                            (double) dataSet.size();
        } else {
            int countPPositive = 0;
            for (int j = 0; j < supportVectorsAlphas.size(); j++) {
                if (alphaPositive <= supportVectorsAlphas.get(j)) {
                    countPPositive++;
                }
            }
            pPositive = (double) countPPositive
                    /
                    (double) dataSet.size();

        }
        dataSet.remove(dataSet.size() - 1);

        prediction.setAlphaPositive((double) alphaPositive);
        prediction.setpPositive(pPositive);

        return prediction;
    }

    private ConfigurationResultEntity pNegativeCalc(ArrayList<DatasetObjectsEntity> dataSet,
                                                    DatasetObjectsEntity testObject,
                                                    ConfigurationResultEntity prediction,
                                                    DatasetConfigurationEntity datasetConfiguration) {
        testObject.setObjectClass((long) -1);
        dataSet.add(testObject);
        svm_model svmModelNegative = this.svmTrainConformalPredict(dataSet, datasetConfiguration);

        int negativeAlphaIndex = -1; // index of the checking object in model
        long alphaNegative = 0; // alpha of the checking object
        int countAlphasNegative = 0; // negative alphas in train dataSet
        ArrayList<Double> negativeSupportVectorsAlphas = new ArrayList<Double>(); // alphas of support vectors where y=-1
        double pNegative = 0;
        //get negative alphas in train dataSet
        for (int k = 0; k < dataSet.size(); k++) {
            if (dataSet.get(k).getObjectClass() == -1) {
                countAlphasNegative++;
            }
        }
        //get index of the checking object in model
        //if negativeAlphaIndex == -1 - alpha of the checking object = 0
        for (int j = 0; j < svmModelNegative.sv_indices.length; j++) {
            if (svmModelNegative.sv_indices[j] == dataSet.size()) {
                negativeAlphaIndex = j;
                break;
            }
        }
        //if negativeAlphaIndex != -1 - alpha of the checking object - support vector
        //svmModelNegative.sv_coef - massive of alpha * y
        if (negativeAlphaIndex != -1) {
            alphaNegative = (long) Math.abs(svmModelNegative.sv_coef[0][negativeAlphaIndex]);
        }

        ArrayList<Double> supportVectorsAlphas = new ArrayList<Double>();
        //found alphas of support vectors where y=-1
        for (int j = 0; j < svmModelNegative.sv_indices.length; j++) {
            if (svmModelNegative.sv_coef[0][j] < 0) {
                negativeSupportVectorsAlphas.add(Math.abs(svmModelNegative.sv_coef[0][j]));
            }
            supportVectorsAlphas.add(Math.abs(svmModelNegative.sv_coef[0][j]));
        }

        // if alpha of the checking object = 0
        if (alphaNegative == 0) {
            pNegative =
                    (double) dataSet.size()
                            /
                            (double) dataSet.size();
        } else {
            int countPNegative = 0;
            for (int j = 0; j < supportVectorsAlphas.size(); j++) {
                if (alphaNegative <= supportVectorsAlphas.get(j)) {
                    countPNegative++;
                }
            }
            pNegative = (double) countPNegative
                    /
                    (double) dataSet.size();
        }
        dataSet.remove(dataSet.size() - 1);

        prediction.setAlphaNegative((double) alphaNegative);
        prediction.setpNegative(pNegative);
        return prediction;
    }

    private svm_model svmTrainConformalPredict(ArrayList<DatasetObjectsEntity> dataSet,
                                               DatasetConfigurationEntity datasetConfiguration) {
        int recordSize = dataSet.size();

        double nodeValues[][] = new double[recordSize][]; //jagged array used to store values
        int nodeIndexes[][] = new int[recordSize][];//jagged array used to store node indexes
        double nodeClassLabels[] = new double[recordSize];//store class lavels

        //Now store data values
        for (int i = 0; i < dataSet.size(); i++) {
            int dataClass;
            if (dataSet.get(i).getObjectClass() == 1)
                dataClass = 1;
            else
                dataClass = -1;
            nodeClassLabels[i] = dataClass;

            LinkedList<Integer> listIndx = new LinkedList<Integer>();
            LinkedList<Double> listVal = new LinkedList<Double>();

            String[] splitObject = dataSet.get(i).getParams().split(",");

            for (int j = 0; j < splitObject.length; j++) {
                listIndx.add(j + 1);
                listVal.add(Double.parseDouble(splitObject[j]));
            }

            if (listVal.size() > 0) {
                nodeValues[i] = new double[listVal.size()];
                nodeIndexes[i] = new int[listIndx.size()];
            }
            for (int m = 0; m < listVal.size(); m++) {
                nodeIndexes[i][m] = listIndx.get(m);
                nodeValues[i][m] = listVal.get(m);
            }
        }

        svm_problem prob = new svm_problem();
        int dataCount = recordSize;
        prob.y = new double[dataCount];
        prob.l = dataCount;
        prob.x = new svm_node[dataCount][];

        for (int i = 0; i < dataCount; i++) {
            prob.y[i] = nodeClassLabels[i];
            double[] values = nodeValues[i];
            int[] indexes = nodeIndexes[i];
            prob.x[i] = new svm_node[values.length];
            for (int j = 0; j < values.length; j++) {
                svm_node node = new svm_node();
                node.index = indexes[j];
                node.value = values[j];
                prob.x[i][j] = node;
            }
        }

        svm_parameter param = new svm_parameter();
        param.probability = datasetConfiguration.getProbability().intValue();
        param.gamma = datasetConfiguration.getGamma();
        param.nu = datasetConfiguration.getNu();
        param.C = datasetConfiguration.getC();
        param.cache_size = 200;
        param.eps = datasetConfiguration.getEps();
        param.degree = datasetConfiguration.getDegree().intValue();

        switch (datasetConfiguration.getKernelParametr().getName()) {
            case (LINEAR):
                param.kernel_type = svm_parameter.LINEAR;
                break;
            case (POLY):
                param.kernel_type = svm_parameter.POLY;
                break;
            case (RBF):
                param.kernel_type = svm_parameter.RBF;
                break;
            case (SIGMOID):
                param.kernel_type = svm_parameter.SIGMOID;
                break;
            case (PRECOMPUTED):
                param.kernel_type = svm_parameter.PRECOMPUTED;
                break;
        }

        switch (datasetConfiguration.getSvmParametr().getName()) {
            case (C_SVC):
                param.svm_type = svm_parameter.C_SVC;
                break;
            case (NU_SVC):
                param.svm_type = svm_parameter.NU_SVC;
                break;
            case (ONE_CLASS):
                param.svm_type = svm_parameter.ONE_CLASS;
                break;
            case (EPSILON_SVR):
                param.svm_type = svm_parameter.EPSILON_SVR;
                break;
            case (NU_SVR):
                param.svm_type = svm_parameter.NU_SVR;
                break;
        }

        svm_model model = svm.svm_train(prob, param);
        return model;
    }

    private ConfigurationResultEntity setPrediction(ConfigurationResultEntity prediction) {
        double confidence = 0;
        double credibility = 0;

        if (prediction.getpPositive() > prediction.getpNegative()) {
            prediction.setPredictClass((long) 1);
            confidence = (double) 1 - prediction.getpNegative();
            credibility = prediction.getpPositive();
        } else if (prediction.getpNegative() > prediction.getpPositive()) {
            prediction.setPredictClass((long) -1);
            confidence = (double) 1 - prediction.getpPositive();
            credibility = prediction.getpNegative();
        } else if (prediction.getpPositive() == prediction.getpNegative()) {
            prediction.setPredictClass((long) 0);
            confidence = (double) 1 - prediction.getpNegative();
            credibility = prediction.getpPositive();

        }

        prediction.setConfidence(confidence);
        prediction.setCredibility(credibility);

        return prediction;
    }
}
